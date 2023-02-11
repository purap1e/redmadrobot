package com.example.bootcamp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdDto {
    private Long id;
    private String title;
    private String description;
    private int price;
    private boolean active;
    private Long winnerUserId;
    private Long adminId;
    private List<String> imageUrls;

}
