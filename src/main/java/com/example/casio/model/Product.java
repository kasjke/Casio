package com.example.casio.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Product {

    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private Long price;
    private String description;

}
