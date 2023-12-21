package com.example.knittingback.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
//@Data is a Lombok annotation that provides a convenient shortcut for generating boilerplate code for Java classes. It generates getters, setters, toString(), equals(), and hashCode() methods for all non-final fields in the class.
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    private long id;
    private String name;
    private String imagePath;
    private String description;
    private BigDecimal price;

//    private Category id_category;
}
