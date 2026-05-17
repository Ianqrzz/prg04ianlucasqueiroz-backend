package br.com.ifba.usuario.service;


import br.com.ifba.usuario.entity.Usuario;

import java.util.List;

public interface UsuarioIService {

    public Usuario save(Usuario usuario);
    public List<Usuario> findAll();
    public Usuario update(Usuario usuario);
    public void delete(Long id);

    public Usuario findByEmail(String email);
    boolean validarLogin(String email, String senha);
}
