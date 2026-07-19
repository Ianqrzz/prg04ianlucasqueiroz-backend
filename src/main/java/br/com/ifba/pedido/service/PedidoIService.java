package br.com.ifba.pedido.service;

import br.com.ifba.pedido.dto.PedidoPostRequestDto;
import br.com.ifba.pedido.entity.Pedido;
import br.com.ifba.pedido.entity.StatusPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoIService {



    public Pedido save(PedidoPostRequestDto pedidoPostRequestDto);
    public Pedido update(Long id,Pedido pedido);
    public void delete(Long id);
    public Page<Pedido> findAll(Pageable pageable);
    public Pedido findById(Long id);
    public Pedido findByNumeroPedido(String numeroPedido);
    public Page<Pedido> findByUsuarioID(Long id, Pageable pageable);
    Pedido atualizarStatus(Long id, StatusPedido novoStatus);
    Pedido cancelarPedido(Long id);
}
