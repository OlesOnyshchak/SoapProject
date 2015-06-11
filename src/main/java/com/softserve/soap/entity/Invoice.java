package com.softserve.soap.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;
import javax.persistence.*;

@Entity
public class Invoice
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true)
    private Integer invoiceId;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "invoice")
    private List<LineItem> lineItems;
    private String status;

   public Invoice() {}

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

   public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", status='" + status + '\'' +
                '}';
    }
}
