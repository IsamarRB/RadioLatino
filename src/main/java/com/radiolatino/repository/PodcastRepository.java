package com.radiolatino.repository;

import com.radiolatino.model.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PodcastRepository extends JpaRepository<Podcast, Long> {
    List<Podcast> findByGeneroNombre(String nombreGenero);
}
