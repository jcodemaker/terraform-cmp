package com.crcloud.cloudros.utils;

/**
 * @Author xingcl
 * @Date 2021/4/22
 */
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author: xingcl
 * @create: 2022/4/6
 */
@Component
public class SpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    private static DefaultSingletonBeanRegistry beanFactoryAware;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringBeanUtil.applicationContext == null) {
            SpringBeanUtil.applicationContext = applicationContext;
            SpringBeanUtil.beanFactoryAware = (DefaultSingletonBeanRegistry)applicationContext.getAutowireCapableBeanFactory();
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static DefaultSingletonBeanRegistry getBeanFactoryAware() {
        return beanFactoryAware;
    }

    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }


    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

    public static void autoWireBean(String beanName, Object bean){
        getBeanFactoryAware().registerSingleton(beanName, bean);
    }


}
