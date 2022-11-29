package com.neko.paginationqr.controller;


import com.neko.paginationqr.model.entity.Anime;
import com.neko.paginationqr.model.response.AnimeResponse;
import com.neko.paginationqr.model.response.WebResponse;
import com.neko.paginationqr.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anime")
public class AnimeController {

    private final AnimeService animeService;

    @Autowired
    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }


    @GetMapping
    public WebResponse<List<Anime>> getAll() {
        List<Anime> animes = animeService.getAllAnime();
        return new WebResponse<>(animes.size(), animes);
    }

    @GetMapping("{field}")
    public WebResponse<List<Anime>> getAllBySorting(@PathVariable String field) {
        List<Anime> animes = animeService.findAnimeWithSorting(field);
        return new WebResponse<>(animes.size(), animes);
    }

    @GetMapping("/pagination")
    public WebResponse<Page<Anime>> getAllWithPagination(@RequestParam("start") int start, @RequestParam("size") int size) {
        Page<Anime> animes = animeService.findAnimeWithPagination(start, size);
        return new WebResponse<>(animes.getSize(), animes);
    }

    @GetMapping("/list")
    public WebResponse<Page<AnimeResponse>> getAllWithPagination(@RequestParam("start") int start, @RequestParam("size") int size, @RequestParam("field") String field) {
        Page<AnimeResponse> animes = animeService.findAnimeWithPaginationAndSorting(start, size,field);
        return new WebResponse<>(animes.getSize(), animes);
    }

    @GetMapping("/genre")
    public WebResponse<Page<AnimeResponse>> getAllWithPaginationAndGenre(@RequestParam("field") String field, Pageable pageable) {
        Page<AnimeResponse> animes = animeService.findAnimeWithPaginationFilterByGenre(field, pageable);
        return new WebResponse<>(animes.getSize(), animes);
    }
}
