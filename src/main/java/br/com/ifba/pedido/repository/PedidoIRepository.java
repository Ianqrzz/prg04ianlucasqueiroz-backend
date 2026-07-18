package br.com.ifba.pedido.repository;

import br.com.ifba.pedido.entity.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PedidoIRepository extends JpaRepository<Pedido, Long> {
    Page<Pedido> findByUsuarioId(Long usuarioId, Pageable pageable);
}
