package com.example.spring.util;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ervin
 * @Date 2023/7/30
 */
@Slf4j
public class ClassUtil {


    public static final String FILE_PROTOCOL = "file";
    public static final String CLASS_SUFFIX = ".class";

    /**
     * 获取包下类集合
     *
     * @param packageName 包名
     * @return class集合
     */
    public static Set<Class<?>> extractPackageClass(String packageName) {
        //获取到类的加载器，通过类加载器获取资源路径
        ClassLoader classLoader = getClassLoader();
        URL url = classLoader.getResource(packageName.replace(".", "/"));
        if (url == null) {
            log.warn("unable to retrieve anything from package: " + packageName);
            return null;
        }
        Set<Class<?>> classSet = null;
        if (FILE_PROTOCOL.equalsIgnoreCase(url.getProtocol())) {
            classSet = new HashSet<>();
            File packageDirectory = new File(url.getPath());
            extractClassFile(classSet, packageDirectory, packageName);
        }
        //TODO 此处可以加入针对其他类型资源的处理
        return classSet;
    }


    /**
     * 递归获取目标package里面的所有class文件(包括子package里的class文件)
     *
     * @param emptyClassSet 装载目标类的集合
     * @param fileSource    文件或者目录
     * @param packageName   包名
     */
    private static void extractClassFile(Set<Class<?>> emptyClassSet, File fileSource, String packageName) {
        if (!fileSource.isDirectory()) {
            return;
        }
        //如果是一个文件夹，则调用其listFiles方法获取文件夹下的文件或文件夹
        File[] listFiles = fileSource.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                if (file.isDirectory()) {
                    return true;
                } else {
                    String absolutePath = file.getAbsolutePath();
                    //如果不是目录，是文件的话，只处理 .class 文件
                    if (absolutePath.endsWith(CLASS_SUFFIX)) {
                        //add to class set
                        addToClassSet(absolutePath);
                    }
                }
                return false;
            }

            //根据class文件的绝对值路径，获取并生成class对象，并放入classSet中
            private void addToClassSet(String absolutePath) {
                //1.从class文件的绝对值路径里提取出包含了package的类名
                //如/Users/baidu/imooc/springframework/sampleframework/target/classes/com/imooc/entity/dto/MainPageInfoDTO.class
                //需要弄成com.imooc.entity.dto.MainPageInfoDTO
                absolutePath = absolutePath.replace(File.separator, ".");
                String className = absolutePath.substring(absolutePath.indexOf(packageName));
                className = className.substring(0, className.lastIndexOf("."));
                //获取 class 对象，并放入classSet中
                Class<?> aClass = loadClass(className);
                emptyClassSet.add(aClass);
            }
        });

        if (listFiles != null) {
            for (File f : listFiles) {
                //递归调用
                extractClassFile(emptyClassSet, f, packageName);
            }
        }
    }


    /**
     * 获取Class对象
     *
     * @param className class全名=package + 类名
     * @return Class
     */
    public static Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException e) {
            log.error("load class error:", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 实例化class
     *
     * @param clazz      Class
     * @param <T>        class的类型
     * @param accessible 是否支持创建出私有class对象的实例
     * @return 类的实例化
     */
    public static <T> T newInstance(Class<?> clazz, boolean accessible) {
        try {
            Constructor<?> declaredConstructor = clazz.getDeclaredConstructor();
            declaredConstructor.setAccessible(accessible);
            return (T) declaredConstructor.newInstance();
        } catch (Exception e) {
            log.error("newInstance error", e);
            throw new RuntimeException(e);
        }
    }

    public static void setField(Field field, Object target, Object value, boolean accessible) {
        field.setAccessible(accessible);
        try {
            field.set(target, value);
        } catch (IllegalAccessException e) {
            log.error("set field error", e);
            throw new RuntimeException(e);
        }
    }


    /**
     * 获取类加载器
     *
     * @return 类加载器
     */
    private static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}
