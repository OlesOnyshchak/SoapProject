package com.softserve.soap.service;

import com.sforce.soap.enterprise.sobject.Invoice__c;
import com.sforce.soap.enterprise.sobject.Line_Item__c;
import com.sforce.soap.enterprise.sobject.Merchandise__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.softserve.soap.config.EntityManagerConfiguration;
import com.softserve.soap.entity.Invoice;
import com.softserve.soap.entity.LineItem;
import com.softserve.soap.entity.Merchandise;
import com.softserve.soap.operation.Operation;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;


public class Transformer
{
    private Operation operation = new Operation();

    public void saveInvoiceObjects()
    {
        EntityManagerConfiguration entityManagerConfiguration = new EntityManagerConfiguration();
        EntityManager entityManager = entityManagerConfiguration.getEntityManager();
        entityManager.getTransaction().begin();
        List<Invoice> invoiceList = transformInvoice(operation.getInvoices());
        entityManager.persist(invoiceList.get(0));
        entityManager.getTransaction().commit();
        entityManagerConfiguration.closeSession();
    }

    public void saveMerchandiseObjects()
    {
        EntityManagerConfiguration entityManagerConfiguration = new EntityManagerConfiguration();
        EntityManager entityManager = entityManagerConfiguration.getEntityManager();
        entityManager.getTransaction().begin();
        List<Merchandise> merchandiseList = transformMerchandise(operation.getMerchandises());
        entityManager.persist(merchandiseList.get(0));
        entityManager.getTransaction().commit();
        entityManagerConfiguration.closeSession();
    }

    private List<Invoice> transformInvoice(SObject[] sObjects)
    {
      List<Invoice>invoiceList = new ArrayList<Invoice>();
        for(SObject s:sObjects){
            Invoice myInvoice = new Invoice();
            Invoice__c salesForceInvoice = (Invoice__c)s;
            myInvoice.setStatus(salesForceInvoice.getStatus__c());
            myInvoice.setLineItems(new ArrayList<LineItem>());
            invoiceList.add(myInvoice);
        }
        return invoiceList;
    }

    private List<Merchandise> transformMerchandise(SObject[] sObjects){
        List<Merchandise>merchandiseList = new ArrayList<Merchandise>();
        for(SObject s:sObjects){
            Merchandise myMerchandise = new Merchandise();
            Merchandise__c salesForceMerchandise = ( Merchandise__c)s;
            myMerchandise.setPrice(salesForceMerchandise.getPrice__c());
            myMerchandise.setQuantity(salesForceMerchandise.getQuantity__c());
            myMerchandise.setLineItems(new ArrayList<LineItem>());
            merchandiseList.add(myMerchandise);
        }
        return merchandiseList;
    }
}
