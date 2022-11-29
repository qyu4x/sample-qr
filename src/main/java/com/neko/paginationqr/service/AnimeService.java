package com.neko.paginationqr.service;

import com.neko.paginationqr.model.entity.Anime;
import com.neko.paginationqr.model.response.AnimeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AnimeService {

    List<Anime> getAllAnime();

    List<Anime> findAnimeWithSorting(String field);

    Page<Anime> findAnimeWithPagination(int offset, int pageSize);

    Page<AnimeResponse> findAnimeWithPaginationAndSorting(int offset, int pageSize, String field);

    Page<AnimeResponse> findAnimeWithPaginationFilterByGenre(String field, Pageable pageable);

}
