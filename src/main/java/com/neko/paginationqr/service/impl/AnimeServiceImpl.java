package com.neko.paginationqr.service.impl;

import com.neko.paginationqr.model.entity.Anime;
import com.neko.paginationqr.model.response.AnimeResponse;
import com.neko.paginationqr.repository.AnimeRepository;
import com.neko.paginationqr.service.AnimeService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;


@Service
public class AnimeServiceImpl implements AnimeService {

    private final AnimeRepository animeRepository;

    @Autowired
    public AnimeServiceImpl(AnimeRepository animeRepository) {
        this.animeRepository = animeRepository;
    }

    @PostConstruct
    private void initDB() {
        List<Anime> animes = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            Anime anime  = Anime.builder()
                    .id(String.format("a-%s", UUID.randomUUID().toString()))
                    .title(String.format("Kaguya sama love is war %s", i))
                    .description("Kaguya chan uwuuu")
                    .rating(ThreadLocalRandom.current().nextDouble(0, 10.0))
                    .genre("Romance")
                    .build();
            animes.add(anime);
        }
        animeRepository.saveAll(animes);
    }

    @Override
    public List<Anime> getAllAnime() {
        return animeRepository.findAll();
    }

    @Override
    public List<Anime> findAnimeWithSorting(String field) {
        return animeRepository.findAll(Sort.by(Sort.Direction.ASC, field));
    }

    @Override
    public Page<Anime> findAnimeWithPagination(int offset, int pageSize) {
        return animeRepository.findAll(PageRequest.of(offset, pageSize));
    }

    @Override
    public Page<AnimeResponse> findAnimeWithPaginationAndSorting(int offset, int pageSize, String field) {
        Page<Anime> page =  animeRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.Direction.ASC, field));
        Page<AnimeResponse> animeResponse = page.map(anime -> new AnimeResponse(anime));
        return animeResponse;
    }

    @Override
    public Page<AnimeResponse> findAnimeWithPaginationFilterByGenre(String field, Pageable pageable) {
        Page<Anime> page =  animeRepository.findAllByGenre(field, pageable);
        Page<AnimeResponse> animeResponse = page.map(anime -> new AnimeResponse(anime));
        return animeResponse;
    }


}
