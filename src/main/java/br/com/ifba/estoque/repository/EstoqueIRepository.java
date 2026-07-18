package br.com.ifba.estoque.repository;

import br.com.ifba.estoque.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstoqueIRepository extends JpaRepository<Estoque, Long> {
    boolean existsByProdutoId(Long id);
    Optional<Estoque> findByProdutoId(Long produtoId);
}
