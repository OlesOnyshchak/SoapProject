package com.softserve.soap;


import com.softserve.soap.request.Request;
import com.sforce.soap.enterprise.sobject.*;
import com.softserve.soap.service.Repository;

import java.util.Arrays;

public class Main
{

    public static void main(String[] args)
    {
        Repository repository = new Repository();
        repository.saveOneInvoice();
       /* repository.saveInvoiceObjects();
        repository.saveMerchandiseObjects();
        repository.saveLineItem();
        repository.closeSession();*/
        repository.closeSession();
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