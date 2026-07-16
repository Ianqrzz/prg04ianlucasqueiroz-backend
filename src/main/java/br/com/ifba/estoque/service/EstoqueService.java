package br.com.ifba.estoque.service;


import br.com.ifba.estoque.entity.Estoque;
import br.com.ifba.estoque.repository.EstoqueIRepository;
import br.com.ifba.infraestructure.exception.BusinessException;
import br.com.ifba.produto.entity.Produto;
import br.com.ifba.produto.repository.ProdutoIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class EstoqueService implements EstoqueIService{

    private static final  Logger logger = Logger.getLogger(EstoqueService.class.getName());


    @Autowired
    private EstoqueIRepository estoqueIRespository;

    @Autowired
    private ProdutoIRepository produtoIRepository;

    @Override
    @Transactional
    public Estoque save(Estoque estoque, Long produtoId) {
        logger.info("Iniciando o registro do Estoque");

        //Verifica se já existe um estoque para esse produto antes de tentar salvar
        if (estoqueIRespository.existsByProdutoId(produtoId)) {
            throw new BusinessException("Já existe um estoque cadastrado para este produto. Use a rota de atualização (update) em vez de criar um novo.");
        }

        Produto produto = produtoIRepository.findById(produtoId)
                        .orElseThrow(() -> new BusinessException("produto não encontrado"));

        estoque.setProduto(produto);

        estoque.setDataUltimaAtualizacao(LocalDateTime.now());

        logger.info("Estoque Registrado com sucesso");
        return estoqueIRespository.save(estoque);
    }

    @Override
    @Transactional
    public Page<Estoque> findAll(Pageable pageable) {

        return estoqueIRespository.findAll(pageable);
    }

    @Override
    @Transactional
    public Estoque update(Long id, Estoque estoque) {
        Estoque estoqueAtualizado = estoqueIRespository.findById(id)
                .orElseThrow(() -> new BusinessException("Estoque não encontrado"));

        estoqueAtualizado.setQuantidade(estoque.getQuantidade());
        estoqueAtualizado.setQuantidadeMinima(estoque.getQuantidadeMinima());
        estoqueAtualizado.setDataUltimaAtualizacao(LocalDateTime.now());

        return estoqueIRespository.save(estoqueAtualizado);
    }

    @Override
    @Transactional
    public void delete(Long id) {

        estoqueIRespository.findById(id)
                .orElseThrow(() -> new BusinessException("Estoque não encontrado"));

        estoqueIRespository.deleteById(id);
    }
}
