package com.softserve.soap.service;

import com.softserve.soap.entity.Invoice;
import com.softserve.soap.operation.Operation;

/**
 * Created by oonysh on 11.06.2015.
 */
public class Transformer {
    private Operation operation = new Operation();
    public Invoice getInvoiceObject(){
        Invoice invoice = new Invoice();
/*        invoice.setStatus();
        invoice.setLineItems();*/
        operation.getInvoices();
        return new Invoice();
    }
}
