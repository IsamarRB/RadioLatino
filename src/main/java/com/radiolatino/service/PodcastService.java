package com.radiolatino.service;

import com.radiolatino.model.Podcast;
import com.radiolatino.repository.PodcastRepository;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
public class PodcastService implements BaseService<Podcast> {

    @Inject
    private PodcastRepository podcastRepository;

    @Override
    public List<Podcast> listarTodos() {
        return podcastRepository.findAll();
    }

    @Override
    public Optional<Podcast> buscarPorId(Long id) {
        return podcastRepository.findById(id);
    }

    @Override
    public Podcast guardar(Podcast podcast) {
        return podcastRepository.save(podcast);
    }

    @Override
    public void eliminar(Long id) {
        podcastRepository.deleteById(id);
    }
}
