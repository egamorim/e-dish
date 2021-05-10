package com.egamorim.edish.application.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderMenuItem {

    private int amount;
    private MenuItem item;
}
