package com.softserve.soap.entity;

import javax.persistence.*;

import java.util.List;

@Entity
public class Merchandise
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = true)
    private Integer merchandiseId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "merchandise")
    private List<LineItem> lineItems;
    private Double price;
    private Double quantity;
    private String name;

    public Merchandise()
    {
    }

    public Integer getMerchandiseId()
    {
        return merchandiseId;
    }

    public void setMerchandiseId(Integer merchandiseId)
    {
        this.merchandiseId = merchandiseId;
    }

    public List<LineItem> getLineItems()
    {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems)
    {
        this.lineItems = lineItems;
    }

    public Double getPrice()
    {
        return price;
    }

    public void setPrice(Double price)
    {
        this.price = price;
    }

    public Double getQuantity()
    {
        return quantity;
    }

    public void setQuantity(Double quantity)
    {
        this.quantity = quantity;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
