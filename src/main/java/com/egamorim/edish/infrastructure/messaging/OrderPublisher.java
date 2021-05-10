package com.egamorim.edish.infrastructure.messaging;

import com.egamorim.edish.infrastructure.messaging.schemas.OrderRequested;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class OrderPublisher {

    private final Processor processor;

    public void publishOrderEvent(OrderRequested order) {
        Message<OrderRequested> message = MessageBuilder.withPayload(order).build();
        processor.output().send(message);
    }
}
