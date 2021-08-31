package com.course.kafkaconsumer.consumer;

import java.net.http.HttpConnectTimeoutException;

import com.course.kafkaconsumer.entity.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class ImageConsumer {

    private final ObjectMapper mapper;

    @KafkaListener(topics = "t_image", containerFactory = "imageRetryContainerFactory")
    public void consumer(String message) throws JsonProcessingException, HttpConnectTimeoutException {
        var image = mapper.readValue(message, Image.class);

        if (image.getType().equalsIgnoreCase("svg")) {
            throw new HttpConnectTimeoutException("Simulate failed api call");
        }
        log.info(">>> Processing image: {}", image);
    }

}
