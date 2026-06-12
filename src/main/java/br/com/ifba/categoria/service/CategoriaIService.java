package br.com.ifba.categoria.service;

import br.com.ifba.categoria.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaIService {


    public Categoria save(Categoria categoria);
    public Page<Categoria> findAll(Pageable pageable);
    public Categoria update(Long id,Categoria categoria);
    public void delete(Long id);
}
