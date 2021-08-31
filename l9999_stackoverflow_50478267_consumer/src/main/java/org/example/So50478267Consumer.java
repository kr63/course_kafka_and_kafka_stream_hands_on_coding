package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;

/*
See https://stackoverflow.com/a/50489352
https://stackoverflow.com/a/55109873
 */
@SpringBootApplication
public class So50478267Consumer {

    public static void main(String[] args) {
        SpringApplication.run(So50478267Consumer.class, args);
    }

    @Bean
    public StringJsonMessageConverter jsonConverter() {
        return new StringJsonMessageConverter();
    }

}
