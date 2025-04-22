package com.radiolatino.service;

import com.radiolatino.model.Podcast;
import com.radiolatino.repository.PodcastRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PodcastService implements BaseService<Podcast> {

    private PodcastRepository podcastRepository;

    public PodcastService() {
        this.podcastRepository = podcastRepository;
    }

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