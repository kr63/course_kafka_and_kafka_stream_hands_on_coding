package com.course.config;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.TopicPartition;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
@AllArgsConstructor
public class KafkaConfig {

    private final KafkaProperties kafkaProperties;

    @Bean
    public ConsumerFactory<Object, Object> consumerFactory() {
        var properties = kafkaProperties.buildConsumerProperties();
        properties.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, "120000");
        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean(name = "invoiceDltFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> invoiceDltFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
            @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection") KafkaOperations<Object, Object> operations) {

        var factory = new ConcurrentKafkaListenerContainerFactory<>();
        configurer.configure(factory, consumerFactory());

        var recover = new DeadLetterPublishingRecoverer(
                operations,
                (r, e) -> new TopicPartition("t_invoice_dlt", r.partition())
        );
        var errorHandler = new SeekToCurrentErrorHandler(recover, new FixedBackOff(3_000, 3));

        factory.setErrorHandler(errorHandler);
        return factory;
    }

}
