package com.course.service;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import com.course.entity.Invoice;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    private AtomicInteger counter = new AtomicInteger(0);

    public Invoice generate() {
        counter.incrementAndGet();
        return Invoice.builder()
                .number("Inv-" + counter)
                .amount(ThreadLocalRandom.current().nextInt(1, 1000))
                .currency("USD")
                .build();
    }

}
