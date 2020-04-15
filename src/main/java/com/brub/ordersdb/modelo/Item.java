package com.brub.ordersdb.modelo;

import javax.persistence.*;


@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    private int amount;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    public Long getId() {
        return id;
    }

    public Item(){
        super();
    }

    public Item(Product product, int amount, Orders order) {
        this.product = product;
        this.amount = amount;
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

}
