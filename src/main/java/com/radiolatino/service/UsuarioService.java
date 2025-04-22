package com.radiolatino.service;

import com.radiolatino.model.Usuario;
import com.radiolatino.repository.UsuarioRepository;
import jakarta.xml.ws.ServiceMode;

import java.util.List;
import java.util.Optional;

@ServiceMode
public class UsuarioService implements BaseService<Usuario> {

    private UsuarioRepository usuarioRepository = null;

    public UsuarioService() {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario login(String usuario, String password) {
    }
}