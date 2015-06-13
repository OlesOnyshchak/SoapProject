package com.softserve.soap.service.impl;

import com.softserve.soap.config.EntityManagerConfiguration;
import com.softserve.soap.dao.impl.InvoiceDao;
import com.softserve.soap.entity.Invoice;
import com.softserve.soap.service.InvoiceService;

import javax.persistence.EntityManager;

public class InvoiceServiceImpl implements InvoiceService
{
    EntityManagerConfiguration entityManagerConfiguration;

    public void saveInvoice(Invoice invoice)
    {
        entityManagerConfiguration = new EntityManagerConfiguration();
        EntityManager entityManager = entityManagerConfiguration.getEntityManager();
        entityManager.getTransaction().begin();
        new InvoiceDao(entityManager).saveOne(invoice);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
