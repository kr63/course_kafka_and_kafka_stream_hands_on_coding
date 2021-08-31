package org.example.consumer;

import org.example.model.Account2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(id = "listen", topics = "so50478267")
    public void listen(Account2 account) {
        System.out.println(account);
    }

}
