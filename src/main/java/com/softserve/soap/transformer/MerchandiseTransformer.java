package com.softserve.soap.transformer;

import com.sforce.soap.enterprise.sobject.Invoice__c;
import com.sforce.soap.enterprise.sobject.Line_Item__c;
import com.sforce.soap.enterprise.sobject.Merchandise__c;
import com.sforce.soap.enterprise.sobject.SObject;
import com.softserve.soap.entity.Invoice;
import com.softserve.soap.entity.LineItem;
import com.softserve.soap.entity.Merchandise;
import com.softserve.soap.request.Request;

import java.util.ArrayList;
import java.util.List;

public class MerchandiseTransformer
{

    public Merchandise transformOne(SObject merchandiseArray[],Request request)
    {
        Merchandise__c oneMerchandise = (Merchandise__c) merchandiseArray[merchandiseArray.length - 1];

        Merchandise merchandise = new Merchandise();
        merchandise.setPrice(oneMerchandise.getPrice__c());
        merchandise.setQuantity(oneMerchandise.getQuantity__c());
        merchandise.setName(oneMerchandise.getName());

        List<LineItem> lineItemList = new ArrayList<LineItem>();
        SObject lineItemArray[] = oneMerchandise.getLine_Items__r().getRecords();

        for (int i = 0; i < lineItemArray.length; i++)
        {
            Line_Item__c oneLineItem = (Line_Item__c) lineItemArray[i];
            LineItem lineItem = new LineItem();
            lineItem.setQuantity(oneLineItem.getQuantity__c());
            lineItem.setUnitPrice(oneLineItem.getUnit_Price__c());
            lineItem.setLineItemName(oneLineItem.getName());
            lineItem.setMerchandise(merchandise);
            Invoice invoice = getInvoiceCorrespondingToLineItem(oneLineItem.getInvoice__c(),request);
            lineItem.setInvoice(invoice);
            lineItemList.add(lineItem);
        }
        merchandise.setLineItems(lineItemList);
        return merchandise;
    }

    public Invoice getInvoiceCorrespondingToLineItem(String id,Request request)
    {
        Invoice invoice = new Invoice();
        SObject invoiceArray[] = request.getInvoiceById(id);
        Invoice__c oneInvoice = (Invoice__c) invoiceArray[invoiceArray.length - 1];
        invoice.setStatus(oneInvoice.getStatus__c());
        return invoice;
    }
}
