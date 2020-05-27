package com.project.delivery.controller;

import com.project.delivery.model.*;
import com.project.delivery.service.DeliveryPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class DeliveryController {

    Logger logger = LoggerFactory.getLogger(DeliveryController.class);

    private DeliveryPersonService deliveryPersonService;

    public DeliveryController() {
        this.deliveryPersonService = DeliveryPersonService.getAssignDeliveryInstance();
    }

    @GetMapping("/delivery/status/{id}")
    ResponseEntity<DeliveryPersonResponse> deliveryStatus(@PathVariable Integer id) {
        try {
            DeliveryPersonResponse deliveryPersonResponse = deliveryPersonService.deliveryPersonStatus(id);
            return ResponseEntity.ok().body(deliveryPersonResponse);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }
        return ResponseEntity.status(500).body(null);
    }

    @PostMapping("/delivery")
    ResponseEntity<OrderStatusEnum> addDelivery(@RequestBody DeliveryPerson deliveryPerson) {
        return ResponseEntity.accepted().body(deliveryPersonService.assignOrder(deliveryPerson));
    }

    @GetMapping("/delivery/active-list")
    ResponseEntity<List<ActiveDeliveryResponse> > activeDeliveryResponses() {
         return ResponseEntity.ok().body(deliveryPersonService.activeDeliveryResponses());
    }



}
