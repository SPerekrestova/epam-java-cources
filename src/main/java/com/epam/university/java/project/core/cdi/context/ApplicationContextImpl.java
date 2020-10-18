package com.epam.university.java.project.core.cdi.context;

import com.epam.university.java.project.core.cdi.bean.BeanDefinition;
import com.epam.university.java.project.core.cdi.bean.BeanDefinitionRegistryImpl;
import com.epam.university.java.project.core.cdi.bean.BeanPropertyDefinition;
import com.epam.university.java.project.core.cdi.bean.BeanRoot;
import com.epam.university.java.project.core.cdi.io.Resource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContextImpl implements ApplicationContext {

    BeanDefinitionRegistryImpl beanDefinitionRegistry = new BeanDefinitionRegistryImpl();
    private Map<Class, Object> singletonCache = new ConcurrentHashMap<>();

    @Override
    public int loadBeanDefinitions(Resource resource) {
        JAXBContext jaxbContext;
        BeanRoot beanRoot;
        List<BeanDefinition> beansList;
        try {
            jaxbContext = JAXBContext.newInstance(BeanRoot.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            beanRoot = (BeanRoot) unmarshaller.unmarshal(resource.getFile());
            beansList = beanRoot.getBeansList();
            for (BeanDefinition beanDefinition : beansList) {
                beanDefinitionRegistry.addBeanDefinition(beanDefinition);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return beanDefinitionRegistry.getRegistrySize();
    }

    @Override
    public int loadBeanDefinitions(Collection<Resource> resources) {
        int numOfBeans = 0;
        for (Resource resource : resources) {
            numOfBeans += loadBeanDefinitions(resource);
        }
        return numOfBeans;
    }

    @Override
    public <T> T getBean(Class<T> beanClass) {
        Class<?> clazz = beanClass;
        String className = clazz.getName().toLowerCase().replaceAll("\\w+\\.", "");
        return getBean(className, beanClass);
    }

    @Override
    public Object getBean(String beanName) {
        Class<?> clazz = null;
        try {            
            clazz = Class.forName(beanDefinitionRegistry.getBeanDefinition(beanName.toLowerCase()).getClassName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        assert clazz != null;
        return getBean(beanName, clazz);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        String scope = beanDefinitionRegistry.getBeanDefinition(beanName.toLowerCase()).getScope();
        T t = null;

        if (scope != null && scope.equals("singleton") && singletonCache.containsKey(beanClass)) {
            return (T) singletonCache.get(beanClass);
        }

        try {
            t = beanClass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        if (beanDefinitionRegistry.getBeanDefinition(beanName.toLowerCase()).getProperties() == null) {
            singletonCache.put(beanClass, t);
            return t;
        }
        List<BeanPropertyDefinition> properties = new LinkedList<>(
                beanDefinitionRegistry.getBeanDefinition(beanName.toLowerCase()).getProperties()
        );

        for (Field field : t.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            for (BeanPropertyDefinition prop : properties) {
                if (field.getName().equals(prop.getName())) {
                    try {
                        Class<?> type = field.getType();
                        String typeName = type.getName().toUpperCase().replaceAll("\\w+\\.", "");
                        if (typeName.equals("INT")) {
                            field.set(t, Integer.parseInt(prop.getValue()));
                        } else if (typeName.equals("STRING")) {
                            field.set(t, prop.getValue());
                        } else {
                            Object dependency = getBean(prop.getRef().toLowerCase());
                            field.set(t, dependency);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (scope != null && scope.equals("singleton") && !singletonCache.containsKey(beanClass)) {
            singletonCache.put(beanClass, t);
        }

        return t;
    }
}
