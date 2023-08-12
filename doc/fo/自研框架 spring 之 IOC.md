
主要实现的功能

- IOC (控制反转)
- DI (依赖注入)

本文只实现最基本的控制反转和依赖注入功能。

# IOC 实现
基本思路如下：

1. 自定义注解：@Controller、@Service、@Repository，用来标记类
2. 定义一个容器，用来存放 Bean 示例，(这里用 ConcurrentHashMap 来实现)
3. 扫描被第一步中的注解标记的类，创建出它们的示例并保存到容器中
## 自定义注解：

定义注解 @Controller、@Service、@Repository、@Component
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Component {
}
```
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Controller {
}
```
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Repository {
}
```
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Service {
}
```

## BeanContainer 容器
定义一个容器 BeanContainer，用来存放加载的 bean 实例，BeanContainer 需要是单例的，这里通过枚举来实现单例：
```java
public class BeanContainer {

    private BeanContainer() {
    }

    /**
     * 
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
    
}
```

定义容器（BeanContainer）属性：
```java
    /**
     * 存放所有被注解标记的目标对象
     */
    private final Map<Class<?>, Object> beanMap = new ConcurrentHashMap<>();

    /**
     * 加载bean的注解列表
     */
    private final static List<Class<? extends Annotation>> BEAN_ANNOTATION = Arrays.asList(Component.class,
            Controller.class, Repository.class, Service.class);
```

核心方法 loadBeans ：扫描加载所有的 bean

1. 根据传入的包名，获取包下类集合 classSet
2. 遍历类集合 classSet ，如果类被注解标记过，就加载到容器中
```java
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
        //通过传入的包名，获取包下类集合
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
```

其中 ClassUtil 提供了两个方法，extractPackageClass() 和 newInstance()：
```java
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
```
```java
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
```

# DI（依赖注入） 

定义 @Autowired
```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
    String value() default "";
}
```

依赖注入具体思路如下：

1. 遍历Bean容器中所有的Class,对象
2. 遍历C1ass对象的所有成员变量
3. 找出被Autowired标记的成员变量
4. 获取这些成员变量的类型
5. 获取这些成员变量的类型在容器里对应的实例
6. 通过反射将对应的成员变量实例注入到成员变量所在类的实例里

依赖注入：
```java
@Slf4j
public class DependencyInjector {
    private BeanContainer beanContainer;

    public DependencyInjector() {
        beanContainer = BeanContainer.getInstance();
    }

    /**
     * 依赖注入
     */
    public void doIoc() {
        //1.遍历Bean容器中所有的Class,对象
        Set<Class<?>> classSet = beanContainer.getClasses();
        if (ValidationUtil.isEmpty(classSet)) {
            log.warn("empty class set in bean container");
            return;
        }
        for (Class<?> clazz : classSet) {
            //2.遍历C1ass对象的所有成员变量
            Field[] fields = clazz.getDeclaredFields();
            if (ValidationUtil.isEmpty(fields)) {
                continue;
            }
            for (Field field : fields) {
                //3.找出被Autowired标记的成员变量
                if (field.isAnnotationPresent(Autowired.class)) {
                    Autowired fieldAnnotation = field.getAnnotation(Autowired.class);
                    String autowiredValue = fieldAnnotation.value();
                    // 4.获取这些成员变量的类型
                    Class<?> fieldClassType = field.getType();
                    // 5.获取这些成员变量的类型在容器里对应的实例
                    Object fieldObject = getFieldInstance(fieldClassType, autowiredValue);
                    if (fieldObject != null) {
                        // 6.通过反射将对应的成员变量实例注入到成员变量所在类的实例里
                        Object beanObject = beanContainer.getBean(clazz);
                        ClassUtil.setField(field, beanObject, fieldObject, true);
                    } else {
                        throw new RuntimeException("unable to inject relevant type，target fieldClass is:" + fieldClassType.getName() + " autowiredValue is : " + autowiredValue);
                    }
                }
            }
        }
    }

    /**
     * 获取字段对象示例
     *
     * @param fieldClass     字段 class 对象
     * @param autowiredValue autowired 注解 value 值
     * @return 字段对象示例
     */
    private Object getFieldInstance(Class<?> fieldClass, String autowiredValue) {
        Object fieldObject = beanContainer.getBean(fieldClass);
        if (fieldObject != null) {
            return fieldClass;
        } else {
            Class<?> implementClass = getImplementClass(fieldClass, autowiredValue);
            if (implementClass != null) {
                return beanContainer.getBean(implementClass);
            } else {
                return null;
            }
        }
    }

    private Class<?> getImplementClass(Class<?> fieldClass, String autowiredValue) {
        Set<Class<?>> classesBySuper = beanContainer.getClassesBySuper(fieldClass);
        if (ValidationUtil.isEmpty(classesBySuper)) {
            return null;
        }
        if (ValidationUtil.isEmpty(autowiredValue)) {
            if (classesBySuper.size() == 1) {
                return classesBySuper.iterator().next();
            } else {
                //如果多于两个实现类且用户未指定其中一个实现类，则抛出异常
                throw new RuntimeException("multiple implemented classes for " + fieldClass.getName() + " please set @Autowired's value to pick one");
            }
        } else {
            for (Class<?> c : classesBySuper) {
                if (autowiredValue.equals(c.getSimpleName())) {
                    return c;
                }
            }
            return null;
        }
    }
}
```

doIoc () 方法：

- 遍历 beanMap 中的 class 对象，将容器中的 bean 实例注入到被 @Autowired 标注的字段中；
- 如果被 @Autowired 标注的属性在 bean 容器中没有对应的类型，将会自动注入其实现类；
- 如果被 @Autowired 标注的属性有多个实现类，则按照 @Autowired 的 value 值注入。

测试见 DependencyInjectorTest
