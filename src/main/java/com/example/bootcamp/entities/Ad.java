package com.example.bootcamp.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ads")
public class Ad {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    private int oldPrice;
    private String leadingUser;

    @Column(name = "price")
    private int price;

    @Column(name = "active")
    private boolean active;

    @OneToMany(cascade = ALL)
    private List<AdFIle> adFiles = Collections.emptyList();

    @Column(name = "winner_user_id")
    private Long winnerUserId;

    @ManyToOne(cascade = ALL)
    private AppUser user;
}
