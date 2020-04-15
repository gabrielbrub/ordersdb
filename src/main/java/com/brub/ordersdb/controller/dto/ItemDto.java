package com.brub.ordersdb.controller.dto;

import com.brub.ordersdb.modelo.Item;

public class ItemDto {
    private int amount;
    private String productName;

    ItemDto(Item item){
        amount = item.getAmount();
        productName = item.getProduct().getName();
    }
}
