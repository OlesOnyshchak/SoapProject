package com.softserve.soap.service;

import com.sforce.soap.enterprise.sobject.Invoice__c;
import com.sforce.soap.enterprise.sobject.Line_Item__c;
import com.sforce.soap.enterprise.sobject.Merchandise__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.softserve.soap.config.EntityManagerConfiguration;
import com.softserve.soap.entity.Invoice;
import com.softserve.soap.entity.LineItem;
import com.softserve.soap.entity.Merchandise;
import com.softserve.soap.request.Request;

import javax.persistence.EntityManager;
import java.awt.image.SampleModel;
import java.util.ArrayList;
import java.util.List;


public class Repository
{
    private Request request = new Request();
    EntityManagerConfiguration entityManagerConfiguration =null;

    public void saveLineItem()
    {
        List<Invoice> invoiceList =transformInvoice(request.getInvoices());
        List<Merchandise> merchandiseList = transformMerchandise(request.getMerchandises());
        entityManagerConfiguration = new EntityManagerConfiguration();
        EntityManager entityManager = entityManagerConfiguration.getEntityManager();
        entityManager.getTransaction().begin();
        List<LineItem> lineItemList = transformLineItem(request.getLineItem());
        LineItem lineItem =lineItemList.get(0);
        lineItem.setInvoice(invoiceList.get(0));
        lineItem.setMerchandise(merchandiseList.get(0));
        entityManager.persist(lineItemList.get(0));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void saveInvoiceObjects()
    {
        entityManagerConfiguration = new EntityManagerConfiguration();
        EntityManager entityManager = entityManagerConfiguration.getEntityManager();
        entityManager.getTransaction().begin();
        List<Invoice> invoiceList = transformInvoice(request.getInvoices());
        entityManager.persist(invoiceList.get(0));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void saveOneInvoice()
    {
        entityManagerConfiguration = new EntityManagerConfiguration();
        EntityManager entityManager = entityManagerConfiguration.getEntityManager();
        entityManager.getTransaction().begin();
        Invoice invoice = transformOneInvoice(request.getInvoices());
        entityManager.merge(invoice);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private Invoice transformOneInvoice(SObject[] sObjects){
        SObject firstInvoice = sObjects[0];
        Invoice__c invoice__c = (Invoice__c)firstInvoice;
        Invoice invoice = new Invoice();
        invoice.setStatus(invoice__c.getStatus__c());
        invoice.setLineItems(getLineItemListById(invoice__c.getId()));
       return invoice;
    }

    private List<LineItem> getLineItemListById(String id){
        SObject[] sObjects = request.getLineItem(id);
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

    public void saveMerchandiseObjects()
    {
        entityManagerConfiguration = new EntityManagerConfiguration();
        EntityManager entityManager = entityManagerConfiguration.getEntityManager();
        entityManager.getTransaction().begin();
        List<Merchandise> merchandiseList = transformMerchandise(request.getMerchandises());
        entityManager.persist(merchandiseList.get(0));
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private List<Invoice> transformInvoice(SObject[] sObjects)
    {
      List<Invoice>invoiceList = new ArrayList<Invoice>();
        for(SObject s:sObjects)
        {
            Invoice myInvoice = new Invoice();
            Invoice__c salesForceInvoice = (Invoice__c)s;
            List<LineItem> lineItems = getLineItemsCorrespondingToInvoice(salesForceInvoice.getId());
            myInvoice.setStatus(salesForceInvoice.getStatus__c());
            myInvoice.setLineItems(lineItems);
            invoiceList.add(myInvoice);
        }
        return invoiceList;
    }

    public List<LineItem> getLineItemsCorrespondingToInvoice(String id)
    {
        SObject[] sObjects = request.getLineItem(id);
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
            myMerchandise.setName(salesForceMerchandise.getName());
            myMerchandise.setLineItems(new ArrayList<LineItem>());
            merchandiseList.add(myMerchandise);
        }
        return merchandiseList;
    }

    private List<LineItem> transformLineItem(SObject[] sObjects)
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

    public void closeSession(){
        entityManagerConfiguration.closeSession();
    }
}





/*public void saveInvoice(){
        entityManagerConfiguration = new EntityManagerConfiguration();
        EntityManager entityManager = entityManagerConfiguration.getEntityManager();
        entityManager.getTransaction().begin();
        List<Invoice>invoiceList = transforInvoice(request.getInv());
    }

    private List<Invoice> transforInvoice(SObject sObject[]){
        List<Invoice> invoiceList = new ArrayList<Invoice>();
        List<LineItem> listItem = new ArrayList<LineItem>();
        for(SObject s:sObject)
        {
            Invoice__c salesForceInvoice = (Invoice__c)s;
            Invoice invoice = new Invoice();
            listItem
            invoice.setLineItems(listItem);
            invoice.setStatus(salesForceInvoice.getStatus__c());



            myInvoice.setStatus(salesForceInvoice.getStatus__c());
            myInvoice.setLineItems(lineItems);
            invoiceList.add(myInvoice);
        }

    }*/