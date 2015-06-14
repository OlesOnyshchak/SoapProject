package com.softserve.soap;

import com.sforce.soap.enterprise.sobject.Invoice__c;
import com.sforce.soap.enterprise.sobject.SObject;
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
    private static final String EXTERNAL_INVOICE_ID = "INV-0007";
    private static final String EXTERNAL_MERCHANDISE_ID = "Desktop";

    public static void main(String[] args)
    {
        request.login();
        saveMerchandise();
        saveInvoice();
        request.logout();
        closeSession();
    }

    private static void saveInvoice()
    {
        Invoice invoice = new InvoiceTransformer().transformOne(
                request.getInvoicesWithLineItem(EXTERNAL_INVOICE_ID),request);
        InvoiceService invoiceService = new InvoiceServiceImpl();
        invoiceService.saveInvoice(invoice);
    }

    private static void saveMerchandise()
    {
        Merchandise merchandise = new MerchandiseTransformer().transformOne(
                request.getMerchandiseWithLineItem(EXTERNAL_MERCHANDISE_ID),request);
        MerchandiseService merchandiseService = new MerchandiseServiceImpl();
        merchandiseService.saveMerchandise(merchandise);
    }

    private static void closeSession()
    {
        EntityManagerConfiguration entityManagerConfiguration = new EntityManagerConfiguration();
        entityManagerConfiguration.closeSession();
    }
}