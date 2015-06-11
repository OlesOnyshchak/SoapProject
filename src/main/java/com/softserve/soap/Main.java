package com.softserve.soap;


import com.softserve.soap.operation.Operation;
import com.softserve.soap.service.Transformer;

public class Main
{
    public static void main(String[] args)
    {
        Transformer transformer = new Transformer();
        transformer.saveInvoiceObjects();
        transformer.saveMerchandiseObjects();

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
