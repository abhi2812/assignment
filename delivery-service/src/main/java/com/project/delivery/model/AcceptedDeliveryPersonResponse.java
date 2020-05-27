package com.project.delivery.model;

public class AcceptedDeliveryPersonResponse extends DeliveryPersonResponse{

    public AcceptedDeliveryPersonResponse() {
        this.setDeliveryStatusResponse();
    }

    protected void setDeliveryStatusResponse() {
        super.orderStatusEnum = OrderStatusEnum.ACCEPTED;
    }
}
