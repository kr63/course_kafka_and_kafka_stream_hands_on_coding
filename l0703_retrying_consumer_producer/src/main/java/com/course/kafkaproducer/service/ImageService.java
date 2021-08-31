package com.course.kafkaproducer.service;

import java.util.concurrent.ThreadLocalRandom;

import com.course.kafkaproducer.entity.Image;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageService {

    private static int counter = 0;

    public Image generateImage(String type) {
        counter++;
        return Image.builder()
                .name("Image-" + counter)
                .type(type)
                .size(ThreadLocalRandom.current().nextLong(100, 10_000))
                .build();
    }

}
