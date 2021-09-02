package com.course;

import com.course.producer.InvoiceProducer;
import com.course.service.InvoiceService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class DltProducer implements CommandLineRunner {

    private final InvoiceService service;
    private final InvoiceProducer producer;

    public static void main(String[] args) {
        SpringApplication.run(DltProducer.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < 10; i++) {
            final var invoice = service.generate();
            if (i >= 5) {
                invoice.setAmount(-1);
            }
            producer.send(invoice);
        }
    }

}
