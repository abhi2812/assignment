package com.project.delivery.model;

public class ActiveDeliveryResponse {
    private OrderStatusEnum orderStatusEnum;
    private Integer deliveryPersonId;

    public ActiveDeliveryResponse(Integer deliveryPersonId) {
        this.deliveryPersonId = deliveryPersonId;
        this.orderStatusEnum = OrderStatusEnum.ACCEPTED;
    }

    public OrderStatusEnum getDeliveryPersonResponse() {
        return orderStatusEnum;
    }

    public void setDeliveryPersonResponse(OrderStatusEnum orderStatusEnum) {
        this.orderStatusEnum = orderStatusEnum;
    }

    public Integer getDeliveryPersonId() {
        return deliveryPersonId;
    }

    public void setDeliveryPersonId(Integer deliveryPersonId) {
        this.deliveryPersonId = deliveryPersonId;
    }
}
