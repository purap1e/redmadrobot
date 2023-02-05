package com.example.bootcamp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ad_files")
@Builder
public class AdFIle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column
    private String name;

    @Column
    private String type;

    @Lob
    @Column(length = 10485760)
    private byte[] data;

//    @Column(name = "ad_id")
//    private Long adId;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ad_id")
//    private Ad ad;
}
