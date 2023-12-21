package com.example.knittingback.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "client")
@Setter
@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    String name;
    String email;
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    Set<OrderEntity> container;
}
