package com.course.kafkaproducer.config;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
@AllArgsConstructor
public class KafkaConfig {

	private final KafkaProperties kafkaProperties;

	@Bean
	public ProducerFactory<String, String> producerFactory() {
		var properties = kafkaProperties.buildProducerProperties();

		properties.put(ProducerConfig.METADATA_MAX_AGE_CONFIG, "180000");

		return new DefaultKafkaProducerFactory<>(properties);
	}

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

}
