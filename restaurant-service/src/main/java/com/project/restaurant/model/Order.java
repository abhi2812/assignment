package com.project.restaurant.model;
import java.util.Date;

public class Order {

    private Date orderDate;

    private Integer orderId;

    private Integer itemsCount;

    private Integer itemId;

    private Integer deliveryPersonId;

    private DeliveryPersonStatus deliveryPersonStatus;

    public Integer getDeliveryPersonId() {
        return deliveryPersonId;
    }

    public DeliveryPersonStatus getDeliveryPersonStatus() {
        return deliveryPersonStatus;
    }

    private Order(OrderBuilder orderBuilder) {
        this.orderDate = orderBuilder.orderDate;
        this.orderId = orderBuilder.orderId;
        this.itemsCount = orderBuilder.itemsCount;
        this.deliveryPersonId = orderBuilder.deliveryPersonId;
        this.deliveryPersonStatus = orderBuilder.deliveryPersonStatus;
        this.itemId = orderBuilder.itemId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public Integer getItemsCount() {
        return itemsCount;
    }

    public static class OrderBuilder {

        public Date orderDate;

        public Integer orderId;

        public Integer itemsCount;

        public Integer itemId;

        public Integer deliveryPersonId;

        public DeliveryPersonStatus deliveryPersonStatus;

        public OrderBuilder setOrderDate(Date orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public OrderBuilder setOrderId(Integer orderId) {
            this.orderId = orderId;
            return this;
        }

        public OrderBuilder setItemsCount(Integer itemsCount) {
            this.itemsCount = itemsCount;
            return this;
        }

        public OrderBuilder setItemId(Integer itemId) {
            this.itemId = itemId;
            return this;
        }
        public OrderBuilder setDeliveryPersonId(Integer deliveryPersonId) {
            this.deliveryPersonId = deliveryPersonId;
            return this;
        }

        public OrderBuilder setDeliveryPersonStatus(DeliveryPersonStatus deliveryPersonStatus) {
            this.deliveryPersonStatus = deliveryPersonStatus;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }

}
