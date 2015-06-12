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

    public void saveLineItem(){
        List<Invoice> invoiceList =transformInvoice(operation.getInvoices());
        List<Merchandise> merchandiseList = transformMerchandise(operation.getMerchandises());
        EntityManagerConfiguration entityManagerConfiguration = new EntityManagerConfiguration();
        EntityManager entityManager = entityManagerConfiguration.getEntityManager();
        entityManager.getTransaction().begin();
        List<LineItem> lineItemList = transformLineItem(operation.getLineItem());
        LineItem lineItem =lineItemList.get(0);
        lineItem.setInvoice(invoiceList.get(0));
        lineItem.setMerchandise(merchandiseList.get(0));
        entityManager.persist(lineItem);
        entityManager.getTransaction().commit();
        entityManagerConfiguration.closeSession();
    }

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
        for(SObject s:sObjects)
        {
            Invoice myInvoice = new Invoice();
            Invoice__c salesForceInvoice = (Invoice__c)s;
           List<LineItem> lineItems=  getLineItemsCorrespondingToInvoice(salesForceInvoice.getId());
            System.out.println(salesForceInvoice.getId());
            myInvoice.setStatus(salesForceInvoice.getStatus__c());
            myInvoice.setLineItems(lineItems);
            invoiceList.add(myInvoice);
        }
        return invoiceList;
    }

    public List<LineItem> getLineItemsCorrespondingToInvoice(String id)
    {
        SObject[] sObjects = operation.getLineItem(id);
        List<LineItem>lineItemList = new ArrayList<LineItem>();
        for(SObject s:sObjects)
        {
            LineItem lineItem = new LineItem();
            Line_Item__c lineItemC = (Line_Item__c)s;
            lineItem.setQuantity(lineItemC.getQuantity__c());
            lineItem.setUnitPrice(lineItemC.getUnit_Price__c());
            lineItemList.add(lineItem);
        }

        return lineItemList;
    }

    private List<Merchandise> transformMerchandise(SObject[] sObjects)
    {
        List<Merchandise>merchandiseList = new ArrayList<Merchandise>();
        for(SObject s:sObjects)
        {
            Merchandise myMerchandise = new Merchandise();
            Merchandise__c salesForceMerchandise = ( Merchandise__c)s;
            myMerchandise.setPrice(salesForceMerchandise.getPrice__c());
            myMerchandise.setQuantity(salesForceMerchandise.getQuantity__c());
            myMerchandise.setLineItems(new ArrayList<LineItem>());
            merchandiseList.add(myMerchandise);
        }
        return merchandiseList;
    }

    public List<LineItem> transformLineItem(SObject[] sObjects)
    {
        List<LineItem>lineItemList = new ArrayList<LineItem>();
        for(SObject s:sObjects)
        {
            LineItem lineItem = new LineItem();
            Line_Item__c salesForceLineItem = (Line_Item__c)s;
            lineItem.setUnitPrice(salesForceLineItem.getUnit_Price__c());
            lineItem.setQuantity(salesForceLineItem.getQuantity__c());
            lineItem.setLineItemName(salesForceLineItem.getName());
            lineItemList.add(lineItem);
        }
        return lineItemList;
    }
}
