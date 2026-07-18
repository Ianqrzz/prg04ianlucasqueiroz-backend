package br.com.ifba.endereco.service;

import br.com.ifba.endereco.entity.Endereco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnderecoIService {
    Endereco save(Endereco endereco, Long idUsuario);
    Endereco update(Long id, Endereco endereco);
    void delete(Long id);
    Endereco findById(Long id);
    Page<Endereco> findAll(Pageable pageable);
    Page<Endereco> findByUsuarioId(Long idUsuario, Pageable pageable);
}