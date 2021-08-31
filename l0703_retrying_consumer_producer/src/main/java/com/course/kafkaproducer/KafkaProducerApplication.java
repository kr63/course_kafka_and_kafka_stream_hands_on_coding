package com.course.kafkaproducer;

import com.course.kafkaproducer.producer.ImageProducer;
import com.course.kafkaproducer.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class KafkaProducerApplication implements CommandLineRunner {

    private final ImageService service;
    private final ImageProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var image1 = service.generateImage("jpg");
        var image2 = service.generateImage("svg");
        var image3 = service.generateImage("png");
        producer.send(image1);
        producer.send(image2);
        producer.send(image3);
    }

}
