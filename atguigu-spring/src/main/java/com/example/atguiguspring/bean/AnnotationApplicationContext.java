package com.example.atguiguspring.bean;

import com.example.atguiguspring.anno.Bean;
import com.example.atguiguspring.anno.Di;

import javax.print.DocFlavor;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

public class AnnotationApplicationContext implements ApplicationContext{
    // 创建map集合，放bean对象
    private static Map<Class, Object> beanFactory = new HashMap<>();
    private static String rootPath;
    // 返回对象
    @Override
    public Object getBean(Class clazz) {
        return beanFactory.get(clazz);
    }

    // 设置包扫描跪着
    // 当前包及其子包，哪个类有@Bean注解，把这个类通过反射实例化
    // 创建有参数构造，传递包路径，设置包扫描规则
    public AnnotationApplicationContext(String basePackage) throws Exception{
        // com.example.atguiguspring
        // 1 把.替换成\
        String packagePath = basePackage.replaceAll("\\.","/");
        // 2 获取包的绝对路径
        try {
            Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(packagePath);
            while (urls.hasMoreElements()) {
                URL url = (URL) urls.nextElement();
                String filePath = URLDecoder.decode(url.getFile(), "utf-8");
                // 获取包前面路径部分，进行字符串截取
                rootPath = filePath.substring(0, filePath.length() - packagePath.length());
                // 包扫描
                loadBean(new File(filePath));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 属性注入
        loadDi();

    }

    // 属性注入
    private void loadDi() throws IllegalAccessException {
        // 实例化的对象都在beanFactory的map集合里面
        // 遍历beanFactory的map集合
        Set<Map.Entry<Class, Object>> entries = beanFactory.entrySet();
        for (Map.Entry<Class, Object> entry : entries) {
            // 获取map集合每个对象，每个对象属性获取到
            Object obj = entry.getValue();

            // 获取对象Class
            Class<?> clazz = obj.getClass();

            // 获取每个对象属性
            Field[] declaredFields = clazz.getDeclaredFields();

            // 遍历得到的每个对象属性数组，得到每个属性
            for (Field field:declaredFields) {
                // 判断属性上面是否有@Di注解
                Di annotation = field.getAnnotation(Di.class);
                if (annotation != null) {
                    // 如果是私有属性，设置可以设置值
                    field.setAccessible(true);
                    // 如果有@Di注解，把对象进行设置注入
                    field.set(obj, beanFactory.get(field.getType()));
                }
            }
        }


    }

    private static void loadBean(File file) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // 判断当前是否是文件夹
        if (file.isDirectory()) {
            // 获取文件夹里面的所有内容
            File[]  childrenFiles = file.listFiles();
            // 判断文件夹里面为空，直接返回
            if(childrenFiles == null || childrenFiles.length == 0) {
                return;
            }
            // 如果文件夹里面不为空，遍历文件夹所有内容
            for (File childrenFile : childrenFiles) {
                //  遍历得到每个file对象，继续判断，如果还是文件夹继续递归
                if (childrenFile.isDirectory()) {
                    // 递归
                    loadBean(childrenFile);
                } else {
                    //  遍历得到file对象不使文件夹，是文件
                    //  得到包路径 + 类名称部分-字符串截取
                    String pathWithCLass = childrenFile.getAbsolutePath().substring(rootPath.length() - 1);
                    //  判断当前文件类型是否为.class
                    if (pathWithCLass.contains(".class")) {
                        //  如果是class类型，就把路径\替换成. 把.class去掉
                        String allName = pathWithCLass.replaceAll("/", ".").
                                replace(".class", "");
                        //  com.atguigu.service.UserServiceImpl
                        //  判断类上面是否有注解@Bean，如果有 就实例化过程
                        //    获取类的Class对象
                        Class<?> clazz = Class.forName(allName);
                        //    判断不是接口
                        if (!clazz.isInterface()) {
                            // 判断类上面是否有注解@Bean
                            Bean annotation = clazz.getAnnotation(Bean.class);
                            if (annotation != null) {
                                // 找到注解，进行实例化
                                Object instance = clazz.getConstructor().newInstance();
                                //  把对象实例化之后，放到map集合beanFactory
                                //  判断当前类 如果有借口，让接口class作为map的key
                                if (clazz.getInterfaces().length > 0) {
                                    beanFactory.put(clazz.getInterfaces()[0], instance);
                                } else {
                                    beanFactory.put(clazz, instance);
                                }
                            }
                        }

                    }

                }

            }

        }

    }
    public static void main(String[] args) {
//        ApplicationContext context = new AnnotationApplicationContext("com.example.atguiguspring");
//        context.getBean()
    }
}
