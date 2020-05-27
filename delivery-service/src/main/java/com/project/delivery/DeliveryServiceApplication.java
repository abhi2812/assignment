package com.project.delivery;

import com.project.delivery.service.DeliveryPersonService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeliveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryServiceApplication.class, args);
		DeliveryPersonService deliveryPersonService = DeliveryPersonService.getAssignDeliveryInstance();
		deliveryPersonService.registerDeliveryService();
	}

}
