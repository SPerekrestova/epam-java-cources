package com.epam.university.java.core.task062;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Collection;
import java.util.Objects;

public class PersonExternalizableImpl implements PersonExternalizable, Externalizable {

    private static final long serialVersionUID = 1L;

    private String fullName;
    private int age;
    private boolean male;
    private PersonExternalizable spouse;
    private Collection<PersonExternalizable> children;

    public PersonExternalizableImpl() {
    }

    @Override
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public void setMale(boolean male) {
        this.male = male;
    }

    @Override
    public void setSpouse(PersonExternalizable spouse) {
        this.spouse = spouse;
    }

    @Override
    public void setChildren(Collection<PersonExternalizable> children) {
        this.children = children;
    }

    @Override
    public void writeExternal(ObjectOutput objectOutput) throws IOException {
        objectOutput.writeObject(fullName);
        objectOutput.writeInt(age);
        objectOutput.writeBoolean(male);
        objectOutput.writeObject(spouse);
        objectOutput.writeObject(children);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void readExternal(ObjectInput objectInput) throws IOException, ClassNotFoundException {
        fullName = (String) objectInput.readObject();
        age = objectInput.readInt();
        male = objectInput.readBoolean();
        spouse = (PersonExternalizable) objectInput.readObject();
        children = (Collection<PersonExternalizable>) objectInput.readObject();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonExternalizableImpl that = (PersonExternalizableImpl) o;
        return age == that.age
                && male == that.male
                && Objects.equals(fullName, that.fullName)
                && Objects.equals(spouse, that.spouse)
                && Objects.equals(children, that.children);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, age, male, spouse, children);
    }
}
