package com.course.kafkaproducer.producer;

import com.course.kafkaproducer.entity.Image;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void send(Image image) throws JsonProcessingException {
        var json = objectMapper.writeValueAsString(image);
        kafkaTemplate.send("t_image", image.getType(), json);
    }

}
