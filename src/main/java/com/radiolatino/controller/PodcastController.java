package com.radiolatino.controller;

import com.radiolatino.model.Podcast;
import com.radiolatino.service.PodcastService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/podcasts")
public class PodcastController {

    private final PodcastService podcastService;

    public PodcastController(PodcastService podcastService) {
        this.podcastService = podcastService;
    }

    @GetMapping
    public List<Podcast> listarPodcasts() {
        return podcastService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Podcast> buscarPodcast(@PathVariable Long id) {
        Optional<Podcast> podcast = podcastService.buscarPorId(id);
        return podcast.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Podcast crearPodcast(@RequestBody Podcast podcast) {
        return podcastService.guardar(podcast);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Podcast> actualizarPodcast(@PathVariable Long id, @RequestBody Podcast podcast) {
        if (!podcastService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        podcast.setId(id);
        return ResponseEntity.ok(podcastService.guardar(podcast));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPodcast(@PathVariable Long id) {
        if (!podcastService.buscarPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        podcastService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}