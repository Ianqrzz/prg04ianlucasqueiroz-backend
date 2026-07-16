package br.com.ifba.estoque.service;

import br.com.ifba.estoque.entity.Estoque;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EstoqueIService {

    Estoque save(Estoque estoque, Long produtoId);
    Page<Estoque> findAll(Pageable pageable);
    Estoque update(Long id,Estoque estoque);
    void delete(Long id);

}
