package br.com.ifba.itempedido.repository;

import br.com.ifba.itempedido.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemPedidoIRepository extends JpaRepository <ItemPedido, Long>{
}
