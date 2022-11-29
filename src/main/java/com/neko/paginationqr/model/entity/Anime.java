package com.neko.paginationqr.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Anime {

    @Id
    private String id;

    private String title;

    private String genre;

    private String description;

    private Double rating;

}
