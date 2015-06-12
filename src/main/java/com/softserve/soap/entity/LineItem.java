package com.softserve.soap.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

@Entity
public class LineItem
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true)

    private Integer lineItemId;

    @ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "invoiceId")
    private Invoice invoice;

    @ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "merchandiseId")
    private Merchandise merchandise;

    private String lineItemName;
    private Double quantity;
    private Double unitPrice;
    public LineItem(){}

    public Integer getLineItemId() {
        return lineItemId;
    }

    public void setLineItemId(Integer lineItemId) {
        this.lineItemId = lineItemId;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public Merchandise getMerchandise() {
        return merchandise;
    }

    public void setMerchandise(Merchandise merchandise) {
        this.merchandise = merchandise;
    }

    public String getLineItemName() {
        return lineItemName;
    }

    public void setLineItemName(String lineItemName) {
        this.lineItemName = lineItemName;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
}
