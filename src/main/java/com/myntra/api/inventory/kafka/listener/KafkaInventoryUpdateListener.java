package com.myntra.api.inventory.kafka.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myntra.api.inventory.logger.ServiceLogger;
import com.myntra.api.inventory.request.InventoryRequest;
import com.myntra.api.inventory.service.InventoryService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class KafkaInventoryUpdateListener {
		
	private InventoryService inventoryService;
	
	private ObjectMapper mapper;

	@KafkaListener(topics = {"#{'${inventory.kafka.topic}'}"},groupId = "#{'${inventory.kafka.groupId}'}")
	public void processKafkaMessage(String request) throws Exception {
		try {
			InventoryRequest inventoryRequest = mapper.readValue(request, InventoryRequest.class);
			inventoryService.updateInventory(inventoryRequest);
		} 
		catch (Exception e) {
			ServiceLogger.error(getClass(), "Inventory Update failed via Kafka. Exception is : " + e.getMessage());
		}
		
	}
	
}
