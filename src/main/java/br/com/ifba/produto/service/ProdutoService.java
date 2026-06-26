package br.com.ifba.produto.service;


import br.com.ifba.categoria.Repository.CategoriaIRepository;
import br.com.ifba.infraestructure.exception.BusinessException;
import br.com.ifba.produto.entity.Produto;
import br.com.ifba.produto.repository.ProdutoRepository;
import br.com.ifba.usuario.service.UsuarioService;
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
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaIRepository categoriaIRepository;

    @Override
    @Transactional
    public Produto save(Produto produto){
        logger.info("Iniciando o Registro do Produto");
        if (StringUtil.isNullOrEmpty(produto.getNome())){
            throw  new BusinessException("Nome não preenchido");
        }

        categoriaIRepository.findById(produto.getCategoria().getId())
                .orElseThrow(() -> new BusinessException("Categoria não encontrada"));
        logger.info("Produto Registrado com sucesso");
        produto.setAtivo(true);
        return produtoRepository.save(produto);
    }

    @Override
    @Transactional
    public Produto update(Long id, Produto produto){
        Produto produtoAtualizado = produtoRepository.findById(id).orElseThrow();
        produtoAtualizado.setNome(produto.getNome());
        produtoAtualizado.setDescricao(produto.getDescricao());
        produtoAtualizado.setPreco(produto.getPreco());
        produtoAtualizado.setImagemUrl(produto.getImagemUrl());
        produtoAtualizado.setAtivo(produto.isAtivo());
        return produtoRepository.save(produtoAtualizado);
    }

    @Override
    @Transactional
    public Page<Produto> findAll(Pageable pageable){
        return produtoRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public void delete(Long id){
        produtoRepository.deleteById(id);
    }
}
