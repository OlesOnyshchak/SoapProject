package com.softserve.soap;


import com.sforce.soap.enterprise.sobject.Invoice__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.softserve.soap.operation.Operation;
import com.softserve.soap.service.Transformer;

public class Main
{
    public static void main(String[] args)
    {
        Operation operation = new Operation();

        SObject sObject[] = operation.getInvoices();
        Invoice__c salesForceInvoice = (Invoice__c)sObject[0];
        System.out.println(salesForceInvoice.getLine_Items__r());
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
