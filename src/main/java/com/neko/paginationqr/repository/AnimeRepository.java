package com.neko.paginationqr.repository;

import com.neko.paginationqr.model.entity.Anime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnimeRepository extends JpaRepository<Anime, String> {

    @Query(nativeQuery = true,
    value = "SELECT * FROM anime WHERE genre = :genre")
    Page<Anime> findAllByGenre(@Param("genre") String genre, Pageable pageable);
}
