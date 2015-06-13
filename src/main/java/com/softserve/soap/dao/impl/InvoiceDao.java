package com.softserve.soap.dao.impl;

import com.softserve.soap.dao.BaseDao;
import com.softserve.soap.entity.Invoice;

import javax.persistence.EntityManager;

public class InvoiceDao implements BaseDao<Invoice>
{
    EntityManager entityManager;

    public InvoiceDao(EntityManager entityManager)
    {
        this.entityManager = entityManager;
    }

    public void saveOne(Invoice entity)
    {
        entityManager.persist(entity);
    }
}
