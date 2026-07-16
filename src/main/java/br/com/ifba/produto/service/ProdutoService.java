package br.com.ifba.produto.service;


import br.com.ifba.categoria.Repository.CategoriaIRepository;
import br.com.ifba.categoria.entity.Categoria;
import br.com.ifba.infraestructure.exception.BusinessException;
import br.com.ifba.produto.entity.Produto;
import br.com.ifba.produto.repository.ProdutoIRepository;
import ch.qos.logback.core.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;
//ainda precisa ser terminado :3
@Service
public class ProdutoService implements ProdutoIService{


    private static final Logger logger = Logger.getLogger(ProdutoService.class.getName());

    @Autowired
    private ProdutoIRepository produtoIRepository;

    @Autowired
    private CategoriaIRepository categoriaIRepository;

    @Override
    @Transactional
    public Produto save(Produto produto, Long categoriaId){
        logger.info("Iniciando o Registro do Produto");

        if (StringUtil.isNullOrEmpty(produto.getNome())){
            throw  new BusinessException("Nome não preenchido");
        }

        Categoria categoria = categoriaIRepository.findById(categoriaId)
                .orElseThrow(() -> new BusinessException("Categoria não encontrada"));

        produto.setCategoria(categoria);
        produto.setAtivo(true);

        logger.info("Produto Registrado com sucesso");
        return produtoIRepository.save(produto);
    }

    @Override
    @Transactional
    public Produto update(Long id, Produto produto, Long categoriaId) {
        // 1. Busca o produto existente no banco
        Produto produtoAtualizado = produtoIRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Produto não encontrado"));

        // 2. Busca a nova categoria no banco para garantir que ela existe
        Categoria novaCategoria = categoriaIRepository.findById(categoriaId)
                .orElseThrow(() -> new BusinessException("Categoria informada não existe"));

        // 3. Atualiza os dados básicos
        produtoAtualizado.setNome(produto.getNome());
        produtoAtualizado.setDescricao(produto.getDescricao());
        produtoAtualizado.setPreco(produto.getPreco());
        produtoAtualizado.setImagemUrl(produto.getImagemUrl());
        produtoAtualizado.setAtivo(produto.isAtivo());

        // 4. Associa a nova categoria validada
        produtoAtualizado.setCategoria(novaCategoria);

        return produtoIRepository.save(produtoAtualizado);
    }

    @Override
    @Transactional
    public Page<Produto> findAll(Pageable pageable){
        return produtoIRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void delete(Long id){
        produtoIRepository.deleteById(id);
    }
}
