package com.softserve.soap;


import com.sforce.soap.enterprise.sobject.Invoice__c;
import com.sforce.soap.enterprise.sobject.Line_Item__c;
import com.sforce.soap.enterprise.sobject.Merchandise__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.softserve.soap.operation.Operation;
import com.softserve.soap.service.Transformer;

public class Main
{
    public static void main(String[] args)
    {
        Operation operation = new Operation();

       /* SObject sObject[] = operation.getLine_Item();
        Line_Item__c salesForceInvoice = (Line_Item__c)sObject[0];*/
        SObject sObject[] = operation.getInvoices();
        Invoice__c invoice__c = (Invoice__c)sObject[0];
        System.out.println(invoice__c.getId());

        SObject sObject1[] = operation.getLine_Item();
        Line_Item__c line_item__c = (Line_Item__c)sObject1[0];
        System.out.println(line_item__c.getName());
        System.out.println(line_item__c.getInvoice__c());

        /*Transformer transformer = new Transformer();
        transformer.saveInvoiceObjects();
        transformer.saveMerchandiseObjects();*/
    }









































    private static void getInfoFromSalesForce(){
        Operation operation = new Operation();

        System.out.println();
        operation.getMerchandises();
        System.out.println();
        operation.getInvoices();
        System.out.println();
        operation.logout();
    }
}
