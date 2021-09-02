package com.course.consumer;

import com.course.entity.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class InvoiceConsumer {

    private final ObjectMapper mapper;

    @KafkaListener(topics = "t_invoice", containerFactory = "invoiceDltFactory")
    public void consume(String message) throws JsonProcessingException {
        var invoice = mapper.readValue(message, Invoice.class);
        if (invoice.getAmount() < 1) {
            throw new IllegalArgumentException("Invalid amount: " + invoice.getAmount() + " , invoice: " + invoice.getNumber());
        }
        log.info("Processing invoice: {}", invoice);
    }

}
