package com.softserve.soap;

import com.softserve.soap.config.EntityManagerConfiguration;
import com.softserve.soap.entity.Invoice;
import com.softserve.soap.entity.Merchandise;
import com.softserve.soap.request.Request;
import com.softserve.soap.service.InvoiceService;
import com.softserve.soap.service.MerchandiseService;
import com.softserve.soap.service.impl.InvoiceServiceImpl;
import com.softserve.soap.service.impl.MerchandiseServiceImpl;
import com.softserve.soap.transformer.InvoiceTransformer;
import com.softserve.soap.transformer.MerchandiseTransformer;

public class Main
{
    private static Request request = new Request();
    private static final String INVOICE_ID = "a032000000LbbhDAAR";
    private static final String MERCHANDISE_ID = "a022000000hbaUyAAI";

    public static void main(String[] args)
    {
        request.login();
        saveInvoice();
        saveMerchandise();
        request.logout();
        closeSession();
    }

    private static void saveInvoice()
    {
        Invoice invoice = new InvoiceTransformer().transformOne(
                request.getInvoicesWithLineItem(INVOICE_ID),request);
        InvoiceService invoiceService = new InvoiceServiceImpl();
        invoiceService.saveInvoice(invoice);
    }

    private static void saveMerchandise()
    {
        Merchandise merchandise = new MerchandiseTransformer().transformOne(
                request.getMerchandiseWithLineItem(MERCHANDISE_ID),request);
        MerchandiseService merchandiseService = new MerchandiseServiceImpl();
        merchandiseService.saveMerchandise(merchandise);
    }

    private static void closeSession()
    {
        EntityManagerConfiguration entityManagerConfiguration = new EntityManagerConfiguration();
        entityManagerConfiguration.closeSession();
    }
}

































 /*    Request request = new Request();
        SObject sObject1[] = request.getInv();
        System.out.println("////");
        System.out.println(Arrays.toString(sObject1));

        Invoice__c invoice__c = (Invoice__c)sObject1[0];




        SObject sObject2[] =invoice__c.getLine_Items__r().getRecords();
        Line_Item__c invoice__c1 = (Line_Item__c) sObject2[0];
        System.out.println(invoice__c1.getQuantity__c());*/
       /* Request request = new Request();
        SObject sObject1[] = request.getInvoices();
        Invoice__c invoice__c = (Invoice__c)sObject1[0];
        System.out.println(invoice__c.getId());

       SObject sObject[] = request.getInvoicesWithLineItem(invoice__c.getId());
        Invoice__c invoice__c1 = (Invoice__c)sObject[0];
        SObject sObject2[] =invoice__c1.getLine_Items__r().getRecords();
        Line_Item__c line_item__c = (Line_Item__c)sObject2[0];
        System.out.println(line_item__c);*/

/*        System.out.println(invoice__c1.getLine_Items__r().getRecords());*/