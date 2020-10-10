package com.epam.university.java.project.core.cdi.bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanDefinitionRegistryImpl implements BeanDefinitionRegistry {
    private Map<String, BeanDefinition> beanCache = new ConcurrentHashMap<>();

    @Override
    public void addBeanDefinition(BeanDefinition definition) {
        beanCache.put(definition.getId(), definition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanId) {
        return beanCache.get(beanId);
    }
}
