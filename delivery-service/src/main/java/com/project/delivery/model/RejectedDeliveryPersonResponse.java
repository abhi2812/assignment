package com.project.delivery.model;

public class RejectedDeliveryPersonResponse extends DeliveryPersonResponse {

    public RejectedDeliveryPersonResponse(OrderStatus orderStatus) {
        this.setDeliveryStatusResponse();
        this.setOrder(orderStatus);
    }

    public void setOrder(OrderStatus orderStatus) {
        super.orderStatus = orderStatus;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    protected void setDeliveryStatusResponse() {
        super.orderStatusEnum = OrderStatusEnum.REJECTED;
    }

}
