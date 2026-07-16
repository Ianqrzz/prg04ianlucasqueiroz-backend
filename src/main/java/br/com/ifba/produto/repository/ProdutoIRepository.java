package br.com.ifba.produto.repository;

import br.com.ifba.produto.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoIRepository extends JpaRepository<Produto, Long> {

    Produto findByNome(String nome);
}
