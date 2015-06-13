package com.softserve.soap.dao.impl;

import com.softserve.soap.dao.BaseDao;
import com.softserve.soap.entity.Merchandise;

import javax.persistence.EntityManager;

public class MerchandiseDao implements BaseDao<Merchandise>
{
    EntityManager entityManager;

    public MerchandiseDao(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    public void saveOne(Merchandise entity)
    {
        entityManager.persist(entity);
    }
}
