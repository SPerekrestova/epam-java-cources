package com.epam.university.java.project.core.cdi.context;

import com.epam.university.java.project.core.cdi.bean.BeanDefinition;
import com.epam.university.java.project.core.cdi.bean.BeanDefinitionRegistryImpl;
import com.epam.university.java.project.core.cdi.bean.BeanRoot;
import com.epam.university.java.project.core.cdi.io.Resource;
import java.util.Collection;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class ApplicationContextImpl implements ApplicationContext {

    BeanDefinitionRegistryImpl beanDefinitionRegistry = new BeanDefinitionRegistryImpl();

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
