package org.example;

import org.apache.kafka.clients.admin.NewTopic;
import org.example.model.Account1;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class So50478267Producer {

    public static void main(String[] args) {
        SpringApplication.run(So50478267Producer.class, args);
    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, Account1> template) {
        return args -> template.send("so50478267", new Account1("foo.inc"));
    }

    @Bean
    public NewTopic topic() {
        return new NewTopic("so50478267", 1, (short) 1);
    }

}
