package com.myntra.api.inventory.kafka.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.myntra.api.inventory.request.InventoryRequest;
import com.myntra.api.inventory.service.InventoryService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class KafkaInventoryUpdateListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaInventoryUpdateListener.class);
		
	private InventoryService inventoryService;

	@KafkaListener(topics = {"#{'${inventory.update.kafka.topic}'}"},groupId = "#{'${inventory.update.kafka.groupId}'}")
	public void processKafkaMessage(InventoryRequest request) throws Exception {
		try {
			inventoryService.updateInventory(request);
		} catch (Exception e) {
			LOGGER.error("Inventory Update failed via Kafka. Exception is : " + e.getMessage());
		}
		
	}
	
}
