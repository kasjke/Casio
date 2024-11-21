package com.example.casio.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString()
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(name = "products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "description")
    private String description;

}
