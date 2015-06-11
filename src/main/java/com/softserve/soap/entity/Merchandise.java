package com.softserve.soap.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
public class Merchandise {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Integer merchandiseId;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "invoice")
    private List<LineItem> lineItems;
    private Integer price;
    private Integer quantity;
   public Merchandise()
    {
    }

}
