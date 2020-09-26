package com.epam.university.java.core.task041;

import java.util.ArrayList;
import java.util.Collection;

public class Task041Impl implements Task041{
    @Override
    public Entity create(Collection<Entity> collection, String value) {
        if (collection == null || value == null) {
            throw new IllegalArgumentException();
        }
        EntityImpl entity = new EntityImpl(0, value);
        collection.add(entity);
        return entity;
    }

    @Override
    public Entity read(Collection<Entity> collection, Entity entity) {
        if (collection == null || entity == null) {
            throw new IllegalArgumentException();
        }
        ArrayList<Entity> list = new ArrayList<>(collection);
        int id = entity.getId();
        return list.get(id);
    }

    @Override
    public void update(Collection<Entity> collection, Entity entity, String value) {
        if (collection == null || value == null || entity == null) {
            throw new IllegalArgumentException();
        }
        if (!collection.contains(entity)) {
            throw new IllegalArgumentException();
        }
        ArrayList<Entity> list = new ArrayList<>(collection);
        int id = entity.getId();
        ((EntityImpl) list.get(id)).setValue(value);
    }

    @Override
    public void delete(Collection<Entity> collection, Entity entity) {
        if (collection == null || entity == null) {
            throw new IllegalArgumentException();
        }
        collection.remove(entity);
    }
}
