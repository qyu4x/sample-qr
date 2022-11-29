package com.neko.paginationqr.model.response;

import com.neko.paginationqr.model.entity.Anime;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimeResponse {

    private String id;

    private String title;

    private Double rating;

    public AnimeResponse(Anime anime) {
        this.id = anime.getId();
        this.title = anime.getTitle();
        this.rating = anime.getRating();
    }
}
