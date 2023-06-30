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
    private static MyDispatcherServlet myDispatcherServlet;

    private MyDispatcherServlet() {
        beanFactoryInit();
        exceptionHandlerFactoryInit();
    }

    public static MyDispatcherServlet getInstance() {
        if (myDispatcherServlet == null) {
            myDispatcherServlet = new MyDispatcherServlet();
        }
        return myDispatcherServlet;
    }
    
    private void beanFactoryInit() {
        Set<Class<?>> configurationClass = myConfigurationScan.getConfigurationClass();
        for (Class<?> clazz : configurationClass) {
            try {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                Method[] methods = clazz.getDeclaredMethods();
                addBeanFactory(instance, methods);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addBeanFactory(Object instance, Method[] methods) {
        for (Method method : methods) {
            MyBean beanAnnotation = method.getDeclaredAnnotation(MyBean.class);
            if (beanAnnotation == null) {
                continue;
            }
            try {
                Object object = method.invoke(instance);
                myBeanFactory.put(object.getClass().getSimpleName(), object);
            } catch (InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void exceptionHandlerFactoryInit() {
        for (String objectString : myBeanFactory.keySet()) {
            Object beanObject = myBeanFactory.get(objectString);
            addExceptionMethod(beanObject);
        }
    }

    private void addExceptionMethod(Object beanObject) {
        Class<?> beanObjectClass = beanObject.getClass();
        Method[] beanMethods = beanObjectClass.getDeclaredMethods();
        for (Method method : beanMethods) {
            MyExceptionHandler exceptionAnno = method.getDeclaredAnnotation(MyExceptionHandler.class);
            if (exceptionAnno == null) {
                continue;
            }
            Map<String, Method> getExceptionMap = myExceptionHandlerFactory.getOrDefault(beanObject, new HashMap<>());
            getExceptionMap.put(exceptionAnno.value(), method);
            myExceptionHandlerFactory.put(beanObject, getExceptionMap);
        }
    }

    public void parseUri(String uri) {
        RequestURI requestURI = new RequestURI(uri);
        boolean isURI = findUri(requestURI.getUri(), requestURI.getParam());
        if (!isURI) {
            System.out.println("잘못된 요청입니다.");
        }
    }

    private boolean findUri(String uri, Map<String, String> paramMap) {
        for (String objectString : myBeanFactory.keySet()) {
            Object beanObject = myBeanFactory.get(objectString);
            if (isBeanFactory(uri, paramMap, beanObject)) {
                return true;
            }
        }
        return false;
    }

    private boolean isBeanFactory(String uri, Map<String, String> paramMap, Object beanObject) {
        Class<?> beanObjectClass = beanObject.getClass();
        Method[] beanMethods = beanObjectClass.getDeclaredMethods();
        for (Method method : beanMethods) {
            MyRequestMapping anno = method.getDeclaredAnnotation(MyRequestMapping.class);
            if (anno == null || !anno.value().equals(uri)) {
                continue;
            }
            Object[] arg = resolveArgument(paramMap, method);
            invokeMethod(beanObject, arg, method);
            return true;
        }
        return false;
    }

    private Object[] resolveArgument(Map<String, String> paramMap, Method objectMethod) {
        Parameter[] parameters = objectMethod.getParameters();
        Object[] argument = new Object[objectMethod.getParameterCount()];
        for (int i=0; i<argument.length; i++) {
            String name = parameters[i].getName();
            String value = paramMap.get(name);
            Class<?> type = parameters[i].getType();
            argument[i] = convertTo(value, type);
        }
        return argument;
    }

    private Object convertTo(String value, Class<?> type) {
        if (type == int.class)
            return Integer.valueOf(value);
        return value;
    }

    private void invokeMethod(Object object, Object[] arg, Method objectMethod) {
        try {
            objectMethod.invoke(object, arg);
        } catch (InvocationTargetException | IllegalAccessException exception) {
            String exceptionCauseName = exception.getCause().getClass().getSimpleName();
            Object excetpioonObject = exception.getCause();
            resolveException(exceptionCauseName, excetpioonObject);
        }
    }

    private void resolveException(String exceptionCauseName, Object excetpioonObject) {
        for (Object exptionObject : myExceptionHandlerFactory.keySet()) {
            Map<String, Method> exceptionMethod = myExceptionHandlerFactory.get(exptionObject);
            for (String exceptionName : exceptionMethod.keySet()) {
                if (!exceptionName.equals(exceptionCauseName)) {
                    continue;
                }
                Method method = exceptionMethod.get(exceptionName);
                try {
                    method.invoke(exptionObject, excetpioonObject);
//                    method.invoke(exptionObject);
                } catch (InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
