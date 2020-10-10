package com.epam.university.java.project.core.cdi.context;

import com.epam.university.java.project.core.cdi.bean.BeanDefinitionImpl;
import com.epam.university.java.project.core.cdi.bean.BeanDefinitionRegistryImpl;
import com.epam.university.java.project.core.cdi.io.Resource;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.Collection;

public class ApplicationContextImpl implements ApplicationContext {
    BeanDefinitionRegistryImpl beanDefinitionRegistry = new BeanDefinitionRegistryImpl();

    @Override
    public int loadBeanDefinitions(Resource resource) {
        JAXBContext jaxbContext;
        BeanDefinitionImpl beanDefinition;
        try {
            jaxbContext = JAXBContext.newInstance(BeanDefinitionImpl.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            beanDefinition = (BeanDefinitionImpl) unmarshaller.unmarshal(new File(String.valueOf(resource.getFile())));
            beanDefinitionRegistry.addBeanDefinition(beanDefinition);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int loadBeanDefinitions(Collection<Resource> resources) {
        return 0;
    }

    // If scope=singleton, we should create new instance outside this method and
    // return a link to this method
    // Create new instance of a class, fill in setters
    // returned complete instance
    @Override
    public <T> T getBean(Class<T> beanClass) {
//        if (cache.containsKey(beanClass)) {
//            return (T) cache.get(beanClass);
//        }
//        Class<? extends T> implClass = beanClass;
//        if (beanClass.isInterface()) {
//            implClass = resource.getImplClass(beanClass);
//        }


        return null;
    }

    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    public <T> T getBean(String beanName, Class<T> beanClass) {
        return null;
    }
}
