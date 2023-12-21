package com.example.knittingback.converter;

import com.example.knittingback.entity.OrderEntity;
import com.example.knittingback.model.Order;
import org.jetbrains.annotations.NotNull;

public class OrderToOrderEntity {
    public OrderEntity convert(@NotNull Order order){
        OrderEntity entity=new OrderEntity().builder()
                .id(order.getId())
                .date(order.getDate())
                .build();
        return entity;
    }
}
