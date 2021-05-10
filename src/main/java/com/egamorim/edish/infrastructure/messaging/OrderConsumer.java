package com.egamorim.edish.infrastructure.messaging;

import com.egamorim.edish.infrastructure.messaging.schemas.OrderRequested;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.stereotype.Component;

@Component
public class OrderConsumer {

    @StreamListener(Processor.INPUT)
    public void consumeNewOrderEvent(OrderRequested order) {
        System.out.println("new order received: " + order);
    }
}
