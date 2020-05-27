package com.project.restaurant.controller;

import com.project.restaurant.model.*;
import com.project.restaurant.service.RestaurantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantController {

    RestaurantService restaurantService;

    public RestaurantController() {
        this.restaurantService = RestaurantService.getInstance();
    }

    @GetMapping("/restaurant/order-status/{id}")
    ResponseEntity<DeliveryPersonResponse> getOrderStatus(@PathVariable Integer id) {
        return ResponseEntity.ok().body(restaurantService.gerOrderStatus(id));
    }

    @PostMapping("/restaurant/order")
    ResponseEntity<String> placeOrder(@RequestBody PlaceOrder placeOrder) {
        if(placeOrder.getItemId() == null || placeOrder.getNumberOfItems() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.accepted().body(restaurantService.placeOrder(placeOrder));
    }

    @PostMapping("/restaurant/update-status")
    ResponseEntity<String> updateDeliveryStatus(@RequestBody DeliveryStatus deliveryStatus) {
        if(deliveryStatus.getDeliveryStatusEnum() == null || deliveryStatus.getOrderId() == null) {
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.accepted().body(restaurantService.updateDeliveryStatus(deliveryStatus).toString());
    }

    @GetMapping("/restaurant/get-active")
    ResponseEntity<List<ActiveDeliveryResponse> > getActiveDeliveryPersons() {
        return ResponseEntity.ok().body(restaurantService.getActiveDeliveryPersons());
    }

    @PostMapping("/restaurant/register-delivery-service")
    ResponseEntity<String> setDeliveryServiceConfig(@RequestBody DeliveryServiceConfig deliveryServiceConfig) {
        return ResponseEntity.ok().body(restaurantService.setDeliveryServiceConfig(deliveryServiceConfig));
    }

}
