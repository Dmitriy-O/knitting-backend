package com.example.knittingback.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Image {
    private long id;
    private String name;
    private String type;
    private String filePath;



}
