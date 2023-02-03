package com.example.bootcamp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseFile {
    private Long id;
    private String title;
    private String description;
    private int price;
    private String fileUrl;
}
