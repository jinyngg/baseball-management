package com.fastcampus.baseballmanagement.core;

import com.fastcampus.baseballmanagement.core.annotation.MyConfiguration;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

// TODO : 06.29 Refactoring
public class MyConfigurationScan {

    private static final String PKG = "com.fastcampus.baseballmanagement";
    private final Set<Class<?>> configurationClass = new HashSet<>();

    public Set<Class<?>> getConfigurationClass() {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource(PKG.replace(".", "/"));
        try {
            File files = new File(url.toURI());
            scanFilePackage(PKG, files);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return configurationClass;
    }

    private void scanFilePackage(String scanPackage, File files) throws Exception {
        for(File file : files.listFiles()) {
            String fileName = file.getName();
            if (file.isDirectory()) {
                scanFilePackage(String.join(".", scanPackage, fileName), file);
            }
            if (fileName.endsWith(".class")) {
                addConfigurationClass(scanPackage, fileName);
            }
        }
    }

    private void addConfigurationClass(String scanPackage, String fileName) throws Exception {
        String classFileName = fileName.replace(".class", "");
        String className = String.format("%s.%s", scanPackage, classFileName);
        Class<?> clazz = Class.forName(className);
        if (!clazz.isAnnotationPresent(MyConfiguration.class)) {
            return;
        }
        configurationClass.add(clazz);
    }

}
