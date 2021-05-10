package com.egamorim.edish.application.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Builder
@Getter
@Document(collection = "menu-item")
public class MenuItem {

    @Id
    private String id;
    private String tittle;
    private String description;
    private BigDecimal price;
    private DishCategory dishCategory;
}
