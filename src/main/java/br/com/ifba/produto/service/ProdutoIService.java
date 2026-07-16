package br.com.ifba.produto.service;

import br.com.ifba.produto.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoIService {


    public Produto save(Produto produto, Long categoriaId);
    public Produto update(Long id,Produto produto, Long categoriaId);
    public Page<Produto> findAll(Pageable pageable);
    public void delete(Long id);
}
