package com.course.producer;

import com.course.entity.Invoice;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InvoiceProducer {

    private final KafkaTemplate<String, String> template;
    private final ObjectMapper mapper;

    public void send(Invoice invoice) throws JsonProcessingException {
        var json = mapper.writeValueAsString(invoice);
        template.send("t_invoice", invoice.getNumber(), json);
    }

}
