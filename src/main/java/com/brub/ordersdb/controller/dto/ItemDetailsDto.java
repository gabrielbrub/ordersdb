package com.brub.ordersdb.controller.dto;

import com.brub.ordersdb.modelo.Item;


public class ItemDetailsDto {
    private int amount;
    private String productName;

    ItemDetailsDto(Item item){
        amount = item.getAmount();
        productName = item.getProduct().getName();
    }
}
