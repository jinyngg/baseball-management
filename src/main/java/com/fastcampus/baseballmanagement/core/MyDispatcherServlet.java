package com.fastcampus.baseballmanagement.core;

import com.fastcampus.baseballmanagement.core.annotation.MyBean;
import com.fastcampus.baseballmanagement.core.annotation.MyRequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MyDispatcherServlet {

    private final MyConfigurationScan myConfigurationScan = new MyConfigurationScan();
    private final Map<String, Object> configMap = new HashMap<>();

    public void init(String pkg) throws Exception {
        Set<Class> classes = myConfigurationScan.configurationScan(pkg);
        for(Class clazz : classes) {
            Object instance = clazz.newInstance();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                Annotation annotation = method.getDeclaredAnnotation(MyBean.class);
                MyBean myBean = (MyBean) annotation;
                if (myBean != null) {
                    Object object = method.invoke(instance);
                    configMap.put(object.toString(), object);
                }
            }
        }
    }

    private void findUri(String uri) throws Exception {
        for(String objectString : configMap.keySet()) {
            Object object = configMap.get(objectString);
            Class<?> objectClass = object.getClass();
            Method[] objectMethods = objectClass.getDeclaredMethods();
            for(Method objectMethod : objectMethods) {
                Annotation anno = objectMethod.getDeclaredAnnotation(MyRequestMapping.class);
                MyRequestMapping requestMapping = (MyRequestMapping) anno;
                if(requestMapping != null && requestMapping.value().equals(uri)) {
                    objectMethod.invoke(object);
                }
            }
        }
    }

    private void findUriParam(String uri, Map<String, String> map) throws Exception {
        for(String objectString : configMap.keySet()) {
            Object object = configMap.get(objectString);
            Class<?> objectClass = object.getClass();
            Method[] objectMethods = objectClass.getDeclaredMethods();
            for(Method objectMethod : objectMethods) {
                Annotation annotation = objectMethod.getDeclaredAnnotation(MyRequestMapping.class);
                MyRequestMapping requestMapping = (MyRequestMapping) annotation;
                if(requestMapping != null && requestMapping.value().equals(uri)) {
                    Parameter[] parameters = objectMethod.getParameters();
                    Object[] arg = new Object[objectMethod.getParameterCount()];
                    for(int i=0; i<arg.length; i++) {
                        String name = parameters[i].getName();
                        Class type = parameters[i].getType();
                        String value = map.get(name);
                        arg[i] = convertTo(value, type);
                    }
                    objectMethod.invoke(object, arg);
                }
            }
        }
    }

    private Object convertTo(String value, Class type) {
        if(type==null || value==null || type.isInstance(value))
            return value;
        if(type==int.class)
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
