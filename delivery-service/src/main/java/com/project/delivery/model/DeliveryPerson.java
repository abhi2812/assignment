package com.project.delivery.model;

import com.sun.istack.internal.NotNull;

public final class DeliveryPerson {

    @NotNull
    private Integer deliveryPersonId;

    @NotNull
    private Integer orderId;

    public Integer getDeliveryPersonId() {
        return deliveryPersonId;
    }

    public void setDeliveryPersonId(Integer deliveryPersonId) {
        this.deliveryPersonId = deliveryPersonId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

}
