package com.project.restaurant.service;

import com.project.restaurant.constants.RestaurantServiceConstants;
import com.project.restaurant.model.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;

public class RestaurantService {

    public static RestaurantService restaurantService;

    HashMap<Integer, Item> itemStock;

    HashMap<Integer, Order> ordersList;

    Queue<Integer> deliveryPersonList;

    DeliveryServiceConfig deliveryServiceConfig;

    private static final Integer RANDOM_SEED = 1000;

    private Random random;

    public static RestaurantService getInstance() {
        if(restaurantService == null) {
            restaurantService = new RestaurantService();
        }
        return restaurantService;
    }

    private RestaurantService(){
        itemStock = new HashMap<>();
        ordersList = new HashMap<>();
        deliveryPersonList = new LinkedList<>();
        random = new Random();
        this.initializeWithRandomData();
    }

    private void initializeWithRandomData() {
        itemStock.put(122, new Item(122, 5));
        itemStock.put(123, new Item(123,15));
        itemStock.put(124, new Item(124, 5));
        itemStock.put(125, new Item(125, 5));
        deliveryPersonList.add(1);
        deliveryPersonList.add(2);
        deliveryPersonList.add(3);
        deliveryPersonList.add(5);
    }

    public String placeOrder(PlaceOrder placeOrder) {
        if(deliveryPersonList.isEmpty()) {
            return RestaurantServiceConstants.DELIVERY_PERSON_NOT_AVAILABLE;
        }
        Integer itemId = placeOrder.getItemId();
        Integer count = placeOrder.getNumberOfItems();
        if(checkItemAvailability(itemId, count)) {
            removeItems(itemId, count);
            return assignOrderToItem(itemId, count).toString();
        }
        return RestaurantServiceConstants.DELIVERY_ITEM_NOT_AVAILABLE;
    }

    private Integer assignOrderToItem(Integer itemId, Integer count) {
        Integer orderId = random.nextInt(RANDOM_SEED);
        Order order = new Order.OrderBuilder()
                .setOrderId(orderId)
                .setItemId(itemId)
                .setItemsCount(count)
                .setDeliveryPersonId(deliveryPersonList.poll())
                .setDeliveryPersonStatus(DeliveryPersonStatus.ACTIVE)
                .setOrderDate(new Date())
                .build();
        final String uri = "http://" + deliveryServiceConfig.getHost() + ":"
                + deliveryServiceConfig.getPort() + "/delivery";
        RestTemplate restTemplate = new RestTemplate();
        DeliveryPerson deliveryPerson = new DeliveryPerson();
        deliveryPerson.setDeliveryPersonId(order.getDeliveryPersonId());
        deliveryPerson.setOrderId(orderId);
        String result = restTemplate.postForObject(uri, deliveryPerson, String.class);
        ordersList.put(orderId, order);
        return orderId;
    }

    public boolean checkItemAvailability(Integer itemId, Integer count) {
        if(this.itemStock.containsKey(itemId)) {
            if(itemStock.get(itemId).getCount() >= count) {
                return true;
            }
        }
        return false;
    }

    public String setDeliveryServiceConfig(DeliveryServiceConfig deliveryServiceConfig) {
        this.deliveryServiceConfig = deliveryServiceConfig;
        return "DONE";
    }

    public void addItems(Integer itemId, Integer count) {
        if(this.itemStock.containsKey(itemId)) {
            Item stockedItem = this.itemStock.get(itemId);
            Integer itemCount = stockedItem.getCount();
            itemCount += count;
            stockedItem.setCount(itemCount);
        } else {
            Item newItem = new Item(itemId, count);
            this.itemStock.put(itemId, newItem);
        }
    }

    public void removeItems(Integer itemId, Integer count) {
        if(this.itemStock.containsKey(itemId)) {
            Item stockedItem = this.itemStock.get(itemId);
            Integer itemCount = stockedItem.getCount();
            itemCount -= count;
            if(itemCount == 0) {
                this.itemStock.remove(itemId);
            } else {
                stockedItem.setCount(itemCount);
            }
        }
    }

    public Integer updateDeliveryStatus(DeliveryStatus deliveryStatus) {
        Order order = ordersList.get(deliveryStatus.getOrderId());
        switch (deliveryStatus.getDeliveryStatusEnum()) {
            case FAILED: {
                //increase the count of items in stock since items would be returned
                Integer itemId = order.getItemId();
                Integer count = order.getItemsCount();
                addItems(itemId, count);
                deliveryPersonList.add(order.getDeliveryPersonId());
                ordersList.remove(deliveryStatus.getOrderId());
                break;
            }
            case DELIVERED: {
                deliveryPersonList.add(order.getDeliveryPersonId());
                ordersList.remove(deliveryStatus.getOrderId());
                break;
            }
        }
        return 1;
    }

    public DeliveryPersonResponse gerOrderStatus(Integer orderId) {
        if(ordersList.get(orderId) == null) {
            return null;
        }
        Integer deliveryPersonId = ordersList.get(orderId).getDeliveryPersonId();
        // final String uri = "http://localhost:8080/delivery/status/" + deliveryPersonId.toString();
        final String uri = "http://" + deliveryServiceConfig.getHost() + ":"
                + deliveryServiceConfig.getPort() + "/delivery/status/" + deliveryPersonId.toString();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<DeliveryPersonResponse> deliveryPersonResponse = restTemplate.getForEntity(uri, DeliveryPersonResponse.class);
        return deliveryPersonResponse.getBody();
    }

    public List<ActiveDeliveryResponse> getActiveDeliveryPersons() {
        // final String uri = "http://localhost:8080/delivery/active-list";
        final String uri = "http://" + deliveryServiceConfig.getHost() + ":"
                + deliveryServiceConfig.getPort() + "/delivery/active-list";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ActiveDeliveryResponse[]> list = restTemplate.getForEntity(uri, ActiveDeliveryResponse[].class);
        List<ActiveDeliveryResponse> searchList= Arrays.asList(list.getBody());
        return searchList;
    }

}
