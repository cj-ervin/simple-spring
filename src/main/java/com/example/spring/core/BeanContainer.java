package com.example.spring.core;

import com.example.spring.core.annotation.Component;
import com.example.spring.core.annotation.Controller;
import com.example.spring.core.annotation.Repository;
import com.example.spring.core.annotation.Service;
import com.example.spring.util.ClassUtil;
import com.example.spring.util.ValidationUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ervin
 * @Date 2023/7/30
 */
@Slf4j
public class BeanContainer {

    private BeanContainer() {
    }

    /**
     * 存放所有被配置标记的目标对象的Map
     */
    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

    /**
     * 加载bean的注解列表
     */
    private final static List<Class<? extends Annotation>> BEAN_ANNOTATION = Arrays.asList(Component.class,
            Controller.class, Repository.class, Service.class);

    /**
     * /**
     * 获取Bean容器实例
     *
     * @return BeanContainer
     */
    public static BeanContainer getInstance() {
        return BeanContainerHolder.HOLDER.instance;
    }

    private enum BeanContainerHolder {
        HOLDER;
        private BeanContainer instance;

        BeanContainerHolder() {
            instance = new BeanContainer();
        }
    }

    private boolean loaded = false;

    /**
     * 容器是否被加载
     *
     * @return 是否被加载
     */
    public boolean isLoaded() {
        String code = "term_order";
        log.warn("BeanContainer has been loaded.");
        return loaded;
    }

    /**
     * bean map size
     *
     * @return size
     */
    public int size() {
        return beanMap.size();
    }

    /**
     * 添加一个class对象及其Bean实例
     *
     * @param clazz Class对象
     * @param bean  Bean实例
     * @return 原有的Bean实例, 没有则返回null
     */
    public Object addBean(Class<?> clazz, Object bean) {
        return beanMap.put(clazz, bean);
    }

    /**
     * 移除一个IOC容器管理的对象
     *
     * @param clazz Class对象
     * @return 删除的Bean实例, 没有则返回null
     */
    public Object removeBean(Class<?> clazz) {
        return beanMap.remove(clazz);
    }

    /**
     * 根据Class对象获取Bean实例
     *
     * @param clazz Class对象
     * @return Bean实例
     */
    public Object getBean(Class<?> clazz) {
        return beanMap.get(clazz);
    }

    /**
     * 获取容器管理的所有Class对象集合
     *
     * @return Class集合
     */
    public Set<Class<?>> getClasses() {
        return beanMap.keySet();
    }

    /**
     * 获取所有Bean集合
     *
     * @return Bean集合
     */
    public Set<Object> getBeans() {
        return new HashSet<>(beanMap.values());
    }

    /**
     * 扫描加载所有Bean
     *
     * @param packageName 包名
     */
    public synchronized void loadBeans(String packageName) {
        //判断容器是否被加载过
        if (loaded) {
            log.warn("extract nothing from packageName" + packageName);
            return;
        }
        Set<Class<?>> classSet = ClassUtil.extractPackageClass(packageName);
        if (ValidationUtil.isEmpty(classSet)) {
            return;
        }

        for (Class<?> c : classSet) {
            for (Class<? extends Annotation> a : BEAN_ANNOTATION) {
                if (c.isAnnotationPresent(a)) {
                    //将目标类本身作为键，目标类的实例作为值，放入到beanMap中
                    beanMap.put(c, ClassUtil.newInstance(c, true));
                }
            }
        }
        loaded = true;
    }


    /**
     * 根据注解筛选出Bean的Class集合
     *
     * @param annotation 注解
     * @return Class集合
     */
    public Set<Class<?>> getClassesByAnnotation(Class<? extends Annotation> annotation) {
        Set<Class<?>> classSet = beanMap.keySet();
        if (ValidationUtil.isEmpty(classSet)) {
            log.warn("nothing in beanMap");
            return null;
        }
        Set<Class<?>> resultSet = new HashSet<>();
        //通过注解筛选被注解标记的class对象，并添加到classSet里
        for (Class<?> c : classSet) {
            if (c.isAnnotationPresent(annotation)) {
                resultSet.add(c);
            }
        }
        return resultSet.isEmpty() ? null : resultSet;
    }

    /**
     * 通过接口或者父类获取实现类或者子类的Class集合，不包括其本身
     *
     * @param classOrInterface 接口Class或者父类Class
     * @return Class集合
     */
    public Set<Class<?>> getClassesBySuper(Class<?> classOrInterface) {
        Set<Class<?>> classSet = beanMap.keySet();
        if (ValidationUtil.isEmpty(classSet)) {
            log.warn("nothing in beanMap");
            return null;
        }
        //判断keySet里的元素是否是传入的接口或者类的子类，如果是，就将其添加到classSet里
        Set<Class<?>> resultSet = new HashSet<>();
        for (Class<?> c : classSet) {
            //判断keySet里的元素是否是传入的接口或者类的子类
            if (classOrInterface.isAssignableFrom(c) && !c.equals(classOrInterface)) {

                resultSet.add(c);
            }
        }
        return resultSet.isEmpty() ? null : resultSet;
    }

}
