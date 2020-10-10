package com.epam.university.java.project.core.cdi.bean;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@XmlRootElement(name = "bean")
public class BeanDefinitionImpl implements BeanDefinition {

    @XmlAttribute(name = "id")
    private String id;

    @XmlAttribute(name = "class")
    private String className;

    @XmlElements({@XmlElement(type = BeanPropertyDefinitionImpl.class, name = "property")})
    private List<BeanPropertyDefinition> properties;

    @XmlAttribute(name = "init")
    private String postConstruct;

    @XmlAttribute(name = "scope")
    private String scope;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public Collection<BeanPropertyDefinition> getProperties() {
        return properties;
    }

    @Override
    public void setProperties(Collection<BeanPropertyDefinition> properties) {
        this.properties = new ArrayList<>(properties);
    }

    @Override
    public String getPostConstruct() {
        return postConstruct;
    }

    @Override
    public void setPostConstruct(String methodName) {
        this.postConstruct = methodName;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
    }
}
