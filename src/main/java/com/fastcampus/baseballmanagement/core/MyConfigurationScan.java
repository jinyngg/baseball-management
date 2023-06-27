package com.fastcampus.baseballmanagement.core;

import com.fastcampus.baseballmanagement.core.annotation.MyConfiguration;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class MyConfigurationScan {

    public Set<Class> configurationScan(String pkg) throws URISyntaxException, ClassNotFoundException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Set<Class> classSet = new HashSet<>();
        URL url = classLoader.getResource(pkg.replace(".", "/"));
        File files = new File(url.toURI());
        scanPackage(pkg, files, classSet);
        return classSet;
    }
    private void scanPackage(String pkg, File files, Set<Class> classSet) throws ClassNotFoundException {
        for(File file : files.listFiles()) {
            String fileName = file.getName();
            if(file.isDirectory()) {
                scanPackage(pkg + "." + fileName, file, classSet);
            } else if(fileName.endsWith(".class")) {
                String className = pkg + "." + fileName.replace(".class", "");
                Class clazz = Class.forName(className);
                if(clazz.isAnnotationPresent(MyConfiguration.class)) {
                    classSet.add(clazz);
                }
            }
        }
    }
}
