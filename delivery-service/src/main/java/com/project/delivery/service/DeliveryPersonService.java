package com.project.delivery.service;

import com.project.delivery.helper.DeliveryPersonResponseFactory;
import com.project.delivery.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DeliveryPersonService {

    Logger logger = LoggerFactory.getLogger(DeliveryPersonService.class);

    private static DeliveryPersonService deliveryPersonService;

    private HashMap<Integer, OrderStatus> deliveryPersonOrderHashMap;


    private DeliveryPersonService() {
        deliveryPersonOrderHashMap = new HashMap<>();
    }

    public static DeliveryPersonService getAssignDeliveryInstance() {
        if(deliveryPersonService == null) {
            deliveryPersonService = new DeliveryPersonService();
        }
        return deliveryPersonService;
    }

    public DeliveryPersonResponse deliveryPersonStatus(Integer deliveryPersonId) {
        OrderStatusEnum orderStatusEnum;
        if(deliveryPersonOrderHashMap.containsKey(deliveryPersonId))
            orderStatusEnum = OrderStatusEnum.REJECTED;
        else orderStatusEnum = OrderStatusEnum.ACCEPTED;
        return DeliveryPersonResponseFactory.getDeliveryPersonResponse(orderStatusEnum,
                deliveryPersonOrderHashMap.get(deliveryPersonId));
    }

    public OrderStatusEnum assignOrder(DeliveryPerson deliveryPerson) {
        OrderStatus orderStatus = new OrderStatus.OrderBuilder()
                .setExpectedDeliveryDate(new Date())
                .setOrderId(deliveryPerson.getOrderId())
                .setDeliveryPersonStatus(OrderStatusEnum.ACCEPTED)
                .build();
        deliveryPersonOrderHashMap.put(deliveryPerson.getDeliveryPersonId(), orderStatus);
        return OrderStatusEnum.ACCEPTED;
    }

    public List<ActiveDeliveryResponse> activeDeliveryResponses() {
        List<ActiveDeliveryResponse> list = new ArrayList<>();
        deliveryPersonOrderHashMap.forEach((deliveryPersonId, orderStatus) -> {
            if(orderStatus.getDeliveryPersonStatus() == OrderStatusEnum.ACCEPTED) {
                list.add(new ActiveDeliveryResponse(deliveryPersonId));
            }
        });
        return list;
    }

    public void registerDeliveryService() {
        final String uri = "http://localhost:8090/restaurant/register-delivery-service";
        RestTemplate restTemplate = new RestTemplate();
        DeliveryServiceConfig deliveryServiceConfig = new DeliveryServiceConfig("localhost", 8080);
        String result = restTemplate.postForObject(uri, deliveryServiceConfig, String.class);
        logger.info("Delivery service registered: " + result);
    }

}
