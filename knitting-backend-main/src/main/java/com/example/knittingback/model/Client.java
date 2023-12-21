package com.example.knittingback.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Client {
    long id;
    String name;
    String email;
}
