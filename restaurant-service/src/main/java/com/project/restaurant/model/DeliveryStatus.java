package com.project.restaurant.model;

public class DeliveryStatus {

    Integer orderId;

    DeliveryStatusEnum deliveryStatusEnum;


    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public DeliveryStatusEnum getDeliveryStatusEnum() {
        return deliveryStatusEnum;
    }

    public void setDeliveryStatusEnum(DeliveryStatusEnum deliveryStatusEnum) {
        this.deliveryStatusEnum = deliveryStatusEnum;
    }
}
