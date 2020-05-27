package com.project.delivery.helper;

import com.project.delivery.model.*;

public class DeliveryPersonResponseFactory {
    public static DeliveryPersonResponse getDeliveryPersonResponse(OrderStatusEnum orderStatusEnum, OrderStatus orderStatus) {
        switch (orderStatusEnum) {
            case ACCEPTED:
                return new AcceptedDeliveryPersonResponse();
            case REJECTED:
                return new RejectedDeliveryPersonResponse(orderStatus);
        }
        return new AcceptedDeliveryPersonResponse();
    }
}
