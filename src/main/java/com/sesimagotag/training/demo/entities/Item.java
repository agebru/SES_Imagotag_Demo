package com.sesimagotag.training.demo.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Item implements Serializable {
    private String id;
    private BigDecimal price;
    private String name;

    public Item(Item item) {
        this.id = item.id;
        this.price = new BigDecimal(item.price.toString());
        this.name = item.name;
    }
}
