package com.softserve.soap.entity;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

@Entity
public class LineItem
{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer invoiceLineId;
    @ManyToOne(fetch = FetchType.LAZY)
    private Invoice invoice;
    @ManyToOne(fetch = FetchType.LAZY)
    private Merchandise merchandise;
    public LineItem(){}
}
