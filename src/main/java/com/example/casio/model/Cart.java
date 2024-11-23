package com.example.casio.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
@Builder
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Table(name = "cart")
@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<CartItem> items;

    @Column(name = "total_price")
    Long totalPrice;

    public Long calculateTotalPrice() {
        return items.stream()
                .mapToLong(cartItem -> cartItem.getProduct().getPrice() * cartItem.getQuantity())
                .sum();
    }
}
