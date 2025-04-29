package com.radiolatino.service;

import com.radiolatino.model.Usuario;
import com.radiolatino.repository.UsuarioRepository;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
public class UsuarioService implements BaseService<Usuario> {

    @Inject
    private UsuarioRepository usuarioRepository;

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

    // Método de login simple (sin cifrado ni tokens)
    public Usuario login(String correo, String password) {
        return usuarioRepository.findByCorreo(correo, password);
    }

    public Optional<Usuario> autenticar(String username, String password) {
    }
}
