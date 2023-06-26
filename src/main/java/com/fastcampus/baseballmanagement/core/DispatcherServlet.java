package com.fastcampus.baseballmanagement.core;

import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DispatcherServlet {

    private ControllerScan controllerScan = new ControllerScan();

    private void findUri(Set<Class> classSet, String uri) throws Exception {
        for(Class clazz : classSet) {
            Object instance = clazz.newInstance();
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method : methods) {
                Annotation annotation = method.getDeclaredAnnotation(RequestMapping.class);
                RequestMapping requestMapping = (RequestMapping) annotation;
                if(requestMapping.name().equals(uri)) {
                    method.invoke(instance);
                }
            }
        }
    }

    private void findUriParam(Set<Class> classSet, String uri, Map<String, String> map) throws Exception {
        for(Class clazz : classSet) {
            Object instance = clazz.newInstance();
            Method[] methods = clazz.getDeclaredMethods();
            for(Method method : methods) {
                Annotation annotation = method.getDeclaredAnnotation(RequestMapping.class);
                RequestMapping requestMapping = (RequestMapping) annotation;
                if(requestMapping.name().equals(uri)) {
                    Parameter[] parameters = method.getParameters();
                    Object[] arg = new Object[method.getParameterCount()];
                    for(int i=0; i<arg.length; i++) {
                        String name = parameters[i].getName();
                        Class type = parameters[i].getType();
                        String value = map.get(name);
                        arg[i] = convertTo(value, type);
                    }
                    method.invoke(instance, arg);
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

    public void parseUri(String pkg, String uri) throws Exception {
        Set<Class> classes = controllerScan.componentScan(pkg);
        String[] uriParam = uri.split("\\?");
        if(uriParam.length > 1) {
            Map<String, String> map = new HashMap<>();
            String[] params = uriParam[1].split("&");
            for(String param : params) {
                String[] values = param.split("=");
                map.put(values[0], values[1]);
            }
            findUriParam(classes, uriParam[0], map);
        } else {
            findUri(classes, uri);
        }
    }

}
