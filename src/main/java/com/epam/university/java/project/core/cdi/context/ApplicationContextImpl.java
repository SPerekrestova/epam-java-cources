package com.epam.university.java.project.core.cdi.context;

import com.epam.university.java.project.core.cdi.bean.BeanDefinitionImpl;
import com.epam.university.java.project.core.cdi.bean.BeanDefinitionRegistryImpl;
import com.epam.university.java.project.core.cdi.bean.BeanPropertyDefinition;
import com.epam.university.java.project.core.cdi.bean.BeanPropertyDefinitionImpl;
import com.epam.university.java.project.core.cdi.io.Resource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ApplicationContextImpl implements ApplicationContext {
    BeanDefinitionRegistryImpl beanDefinitionRegistry = new BeanDefinitionRegistryImpl();

    @Override
    public int loadBeanDefinitions(Resource resource) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        prepareXmlDoc(factory, resource);
        return 0;
    }


    @Override
    public int loadBeanDefinitions(Collection<Resource> resources) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        for (Resource res : resources) {
            prepareXmlDoc(factory, res);
        }
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

    private void prepareXmlDoc(DocumentBuilderFactory factory, Resource res) {
        DocumentBuilder builder;
        Document document = null;
        try {
            File xmlFile = new File(String.valueOf(res.getFile()));
            builder = factory.newDocumentBuilder();
            document = builder.parse(xmlFile);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        assert document != null;
        scanXmlDoc(document);
    }

    private void scanXmlDoc(Document document) {
        List<BeanPropertyDefinition> properties;
        BeanDefinitionImpl bean;
        assert document != null;
        document.getDocumentElement().normalize();
        NodeList nList = document.getElementsByTagName("bean");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node node = nList.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                bean = new BeanDefinitionImpl();
                bean.setId(eElement.getAttribute("id"));
                bean.setClassName(eElement.getAttribute("class"));
                bean.setPostConstruct(eElement.getAttribute("init"));
                bean.setScope(eElement.getAttribute("scope"));
                properties = getBeanPropertyDefinitions(eElement);
                bean.setProperties(properties);
                beanDefinitionRegistry.addBeanDefinition(bean);
            }
        }
    }

    private List<BeanPropertyDefinition> getBeanPropertyDefinitions(Element eElement) {
        List<BeanPropertyDefinition> properties;
        properties = new ArrayList<>();
        NodeList propList = eElement.getElementsByTagName("property");
        for (int k = 0; k < propList.getLength(); k++) {
            NamedNodeMap prop = eElement.getElementsByTagName("property").item(k).getAttributes();
            BeanPropertyDefinitionImpl beanProperty = new BeanPropertyDefinitionImpl();
            for (int i = 0; i < prop.getLength(); i++) {
                String name = prop.item(i).getNodeName();
                switch (name) {
                    case "name":
                        beanProperty.setName(prop.item(i).getNodeValue());
                        break;
                    case "value":
                        beanProperty.setValue(prop.item(i).getNodeValue());
                        break;
                    case "ref":
                        beanProperty.setRef(prop.item(i).getNodeValue());
                        break;
                    default:
                        break;
                }
            }
            properties.add(beanProperty);
        }
        return properties;
    }
}
