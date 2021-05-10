package com.egamorim.edish.application.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Document(collection = "order")
public class Order {

    private UUID id;
    private UUID merchantId;
    private LocalDateTime startedAt;
    private List<OrderMenuItem> content;

}
