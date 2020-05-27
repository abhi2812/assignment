package com.project.restaurant.model;

public class ActiveDeliveryResponse {
    public OrderStatusEnum getOrderStatusEnum() {
        return orderStatusEnum;
    }

    public void setOrderStatusEnum(OrderStatusEnum orderStatusEnum) {
        this.orderStatusEnum = orderStatusEnum;
    }

    private OrderStatusEnum orderStatusEnum;
    private Integer deliveryPersonId;


    public Integer getDeliveryPersonId() {
        return deliveryPersonId;
    }

    public void setDeliveryPersonId(Integer deliveryPersonId) {
        this.deliveryPersonId = deliveryPersonId;
    }
}
