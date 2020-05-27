package com.project.restaurant.model;


public class DeliveryPersonResponse {

    protected OrderStatus orderStatus;

    protected String deliveryPersonStatus;

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setDeliveryPersonStatus(String deliveryPersonStatus) {
        this.deliveryPersonStatus = deliveryPersonStatus;
    }

    public String getDeliveryPersonStatus() {
        return deliveryPersonStatus;
    }
}
