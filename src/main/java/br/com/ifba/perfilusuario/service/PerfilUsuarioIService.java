package br.com.ifba.perfilusuario.service;

import br.com.ifba.perfilusuario.entity.PerfilUsuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PerfilUsuarioIService {

    PerfilUsuario save(PerfilUsuario perfilUsuario);

    Page<PerfilUsuario> findAll(Pageable pageable);

    PerfilUsuario update(Long id, PerfilUsuario perfilUsuario);

    void delete(Long id);
}