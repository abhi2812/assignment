package com.project.delivery.model;

public abstract class DeliveryPersonResponse {

    protected OrderStatus orderStatus;

    protected OrderStatusEnum orderStatusEnum;

    protected abstract void setDeliveryStatusResponse();

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public OrderStatusEnum getDeliveryPersonStatus() {
        return orderStatusEnum;
    }
}
