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

public class InvoiceTransformer
{

    public Invoice transformOne(SObject invoiceArray[],Request request)
    {
        List<LineItem> lineItemList = new ArrayList<LineItem>();
        Invoice__c oneInvoice = (Invoice__c) invoiceArray[invoiceArray.length - 1];
        Invoice invoice = new Invoice();
        invoice.setStatus(oneInvoice.getStatus__c());
        SObject lineItemArray[] = oneInvoice.getLine_Items__r().getRecords();

        for (int i = 0; i < lineItemArray.length; i++)
        {
            LineItem lineItem = new LineItem();
            Line_Item__c oneLineItem = (Line_Item__c) lineItemArray[i];
            lineItem.setQuantity(oneLineItem.getQuantity__c());
            lineItem.setUnitPrice(oneLineItem.getUnit_Price__c());
            lineItem.setLineItemName(oneLineItem.getName());
            Merchandise merchandise = getMerchandiseCorrespondingToLineItem(oneLineItem.getMerchandise__c(),request);
            lineItem.setMerchandise(merchandise);
            lineItem.setInvoice(invoice);
            lineItemList.add(lineItem);
        }
        invoice.setLineItems(lineItemList);
        return invoice;
    }

    private Merchandise getMerchandiseCorrespondingToLineItem(String id,Request request)
    {
        Merchandise merchandise = new Merchandise();
        SObject merchandiseArray[] = request.getMerchandiseById(id);
        Merchandise__c oneMerchandise = (Merchandise__c) merchandiseArray[merchandiseArray.length - 1];
        merchandise.setName(oneMerchandise.getName());
        System.out.println(id);
        merchandise.setPrice(oneMerchandise.getPrice__c());
        merchandise.setQuantity(oneMerchandise.getQuantity__c());
        return merchandise;
    }
}
