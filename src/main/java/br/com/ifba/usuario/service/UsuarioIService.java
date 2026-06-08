package br.com.ifba.usuario.service;


import br.com.ifba.usuario.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioIService {

    public Usuario save(Usuario usuario);
    public Page<Usuario> findAll(Pageable pageable);
    public Usuario update(Long id,Usuario usuario);
    public void delete(Long id);
    public Usuario findByEmail(String email);
    boolean validarLogin(String email, String senha);
}
