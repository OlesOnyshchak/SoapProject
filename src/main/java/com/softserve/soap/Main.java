package com.softserve.soap;

import com.softserve.soap.config.EntityManagerConfiguration;
import com.softserve.soap.entity.Invoice;
import com.softserve.soap.operation.Operation;
import org.hibernate.Session;

import javax.persistence.EntityManager;

public class Main
{
    public static void main(String[] args)
    {
        addDataToDataBase();

    }
    private static void addDataToDataBase()
    {
        EntityManager entityManager = EntityManagerConfiguration.getEntityManager();
        Invoice invoice = new Invoice();
        invoice.setStatus("Ok");
        entityManager.persist(invoice);
        EntityManagerConfiguration.closeSession();
    }

    private static void getInfoFromSalesForce(){
        Operation operation = new Operation();
        operation.logIn();
        System.out.println();
        operation.getMerchandiseFields();
        System.out.println();
        operation.getInvoices();
        System.out.println();
        operation.logout();
    }
}
