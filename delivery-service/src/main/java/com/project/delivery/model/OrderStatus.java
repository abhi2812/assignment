package com.project.delivery.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;

public class OrderStatus {

    private Date expectedDeliveryDate;

    private Integer orderId;

    private OrderStatusEnum orderStatusEnum;

    private OrderStatus(OrderBuilder orderBuilder) {
        this.expectedDeliveryDate = orderBuilder.expectedDeliveryDate;
        this.orderId = orderBuilder.orderId;
        this.orderStatusEnum = orderBuilder.orderStatusEnum;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    @JsonIgnore
    public OrderStatusEnum getDeliveryPersonStatus() { return orderStatusEnum; }

    public static class OrderBuilder {

        public Date expectedDeliveryDate;

        public Integer orderId;

        public OrderStatusEnum orderStatusEnum;

        public OrderBuilder setExpectedDeliveryDate(Date expectedDeliveryDate) {
            this.expectedDeliveryDate = expectedDeliveryDate;
            return this;
        }

        public OrderBuilder setOrderId(Integer orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderBuilder setDeliveryPersonStatus(OrderStatusEnum deliveryPersonStatus) {
            this.orderStatusEnum = deliveryPersonStatus;
            return this;
        }

        public OrderStatus build() {
            return new OrderStatus(this);
        }
    }

}
