package com.egamorim.edish.presentation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class OrderItemDTO {
    private String id;
    private int amount;
}
