package com.myntra.api.inventory.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.myntra.api.inventory.exception.KafkaListnerErrorHandler;
import com.myntra.api.inventory.request.InventoryRequest;

@Configuration
public class KafkaConsumerConfig {
	
	@Value("${inventory.kafka.servers}")
	private String KAFKA_BOOTSTRAP_SERVERS;
	
	@Value("${inventory.kafka.username}")
	private String KAFKA_USERNAME;
	
	@Value("${inventory.kafka.password}")
	private String KAFKA_PW;
	
	@Value("${inventory.kafka.groupId}")
	private String KAFKA_CONSUMER_GROUPID;
	
	@Bean
	CommonErrorHandler commonErrorHandler() {
	    return new KafkaListnerErrorHandler();
	}
	
	@Bean
	public ConsumerFactory<String, String> inventoryUpdateConsumerFactory() {
		Map<String, Object> configProps = new HashMap<>();
		configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_BOOTSTRAP_SERVERS);
		configProps.put(SaslConfigs.SASL_JAAS_CONFIG,
				"org.apache.kafka.common.security.scram.ScramLoginModule required username= \"" + KAFKA_USERNAME
						+ "\" password=\"" + KAFKA_PW + "\";");
		configProps.put(SaslConfigs.SASL_MECHANISM, "SCRAM-SHA-256");
		configProps.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SASL_SSL");
		configProps.put(ConsumerConfig.GROUP_ID_CONFIG, KAFKA_CONSUMER_GROUPID);
		return new DefaultKafkaConsumerFactory<>(configProps, new StringDeserializer(), new StringDeserializer());
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(inventoryUpdateConsumerFactory());
		factory.setCommonErrorHandler(commonErrorHandler());
		return factory;
	}

}
