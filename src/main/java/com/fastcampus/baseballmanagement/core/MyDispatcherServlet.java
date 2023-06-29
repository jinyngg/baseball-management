package com.fastcampus.baseballmanagement.core;

import com.fastcampus.baseballmanagement.core.annotation.MyBean;
import com.fastcampus.baseballmanagement.core.annotation.MyExceptionHandler;
import com.fastcampus.baseballmanagement.core.annotation.MyRequestMapping;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyDispatcherServlet {


    private final MyConfigurationScan myConfigurationScan = new MyConfigurationScan();
    private final Map<String, Object> myBeanFactory = new HashMap<>();
    private final Map<Object, Map<String, Method>> myExceptionHandlerFactory = new HashMap<>();


    // TODO : 23.06.28
    //  1. 생성자를 싱글톤으로 바꾸고 호출 시에 init() 을 호출하도록 변경 필요 -> Main 에서 init() 을 할 필요 없도록
    //  2. configurationInit, exceptionHandlerInit 을 호출해주는 메서드 생성 필요
    //  3. final 로 선언한 변수들 생성자 호출에 초기화 하도록
    //  4. 전체적이 코드 리팩토링 필요
    public void configurationInit() throws Exception {
        Set<Class<?>> classes = myConfigurationScan.getConfigurationClass();
        for (Class<?> clazz : classes) {
            Object instance = clazz.getDeclaredConstructor().newInstance();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                MyBean annotation = method.getDeclaredAnnotation(MyBean.class);
                if (annotation != null) {
                    Object object = method.invoke(instance);
                    myBeanFactory.put(object.toString(), object);
                }
            }
        }
        exceptionHandlerInit();
    }

    private void exceptionHandlerInit() {
        for(String objectString : myBeanFactory.keySet()) {
            Object object = myBeanFactory.get(objectString);
            Class<?> objectClass = object.getClass();
            Method[] objectMethods = objectClass.getDeclaredMethods();
            for (Method objectMethod : objectMethods) {
                MyExceptionHandler exceptionAnno = objectMethod.getDeclaredAnnotation(MyExceptionHandler.class);

                if(exceptionAnno == null) continue;

                Map<String, Method> getMap = myExceptionHandlerFactory.getOrDefault(object, new HashMap<>());
                getMap.put(exceptionAnno.value(), objectMethod);
                myExceptionHandlerFactory.put(object, getMap);
            }
        }
    }

    private void findUri(String uri) {
        for(String objectString : myBeanFactory.keySet()) {
            Object object = myBeanFactory.get(objectString);
            Class<?> objectClass = object.getClass();
            Method[] objectMethods = objectClass.getDeclaredMethods();
            for(Method objectMethod : objectMethods) {
                MyRequestMapping anno = objectMethod.getDeclaredAnnotation(MyRequestMapping.class);
                if(anno != null && anno.value().equals(uri)) {
                    invokeMethod(object, objectMethod);
                }
            }
        }
    }

    private void invokeMethod(Object object, Method objectMethod) {
        try {
            objectMethod.invoke(object);
        }
        catch (InvocationTargetException | IllegalAccessException exception) {
            String exceptionCauseName = exception.getCause().getClass().getSimpleName();
            for(Object exptionObject : myExceptionHandlerFactory.keySet()) {
                Map<String, Method> exceptionMethod = myExceptionHandlerFactory.get(exptionObject);
                for(String exceptionName : exceptionMethod.keySet()) {
                    if (exceptionName.equals(exceptionCauseName)) {
                        Method method = exceptionMethod.get(exceptionName);
                        invokeMethod(exptionObject, method);
                    }
                }
            }
        }
    }

    private void findUriParam(String uri, Map<String, String> map) throws Exception {
        for(String objectString : myBeanFactory.keySet()) {
            Object object = myBeanFactory.get(objectString);
            Class<?> objectClass = object.getClass();
            Method[] objectMethods = objectClass.getDeclaredMethods();
            for(Method objectMethod : objectMethods) {
                MyRequestMapping anno = objectMethod.getDeclaredAnnotation(MyRequestMapping.class);
                if(anno != null && anno.value().equals(uri)) {
                    Parameter[] parameters = objectMethod.getParameters();
                    Object[] arg = new Object[objectMethod.getParameterCount()];
                    for(int i=0; i<arg.length; i++) {
                        String name = parameters[i].getName();
                        Class<?> type = parameters[i].getType();
                        String value = map.get(name);
                        arg[i] = convertTo(value, type);
                    }
                    objectMethod.invoke(object, arg);
                }
            }
        }
    }

    private Object convertTo(String value, Class<?> type) {
        if (type == int.class)
            return Integer.valueOf(value);
        return value;
    }

    public void parseUri(String uri) throws Exception {
        String[] uriParam = uri.split("\\?");
        if(uriParam.length > 1) {
            Map<String, String> map = new HashMap<>();
            String[] params = uriParam[1].split("&");
            for(String param : params) {
                String[] values = param.split("=");
                map.put(values[0], values[1]);
            }
            findUriParam(uriParam[0], map);
        } else {
            findUri(uri);
        }
    }
}
