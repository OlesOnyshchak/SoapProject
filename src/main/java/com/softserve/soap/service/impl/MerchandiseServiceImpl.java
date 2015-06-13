package com.softserve.soap.service.impl;

import com.softserve.soap.config.EntityManagerConfiguration;
import com.softserve.soap.dao.impl.MerchandiseDao;
import com.softserve.soap.entity.Merchandise;
import com.softserve.soap.service.MerchandiseService;

import javax.persistence.EntityManager;

public class MerchandiseServiceImpl implements MerchandiseService
{
    EntityManagerConfiguration entityManagerConfiguration;

    public void saveMerchandise(Merchandise merchandise)
    {
        entityManagerConfiguration = new EntityManagerConfiguration();
        EntityManager entityManager = entityManagerConfiguration.getEntityManager();
        entityManager.getTransaction().begin();
        new MerchandiseDao(entityManager).saveOne(merchandise);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
