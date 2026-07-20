package br.com.ifba.pedido.service;


import br.com.ifba.endereco.entity.Endereco;
import br.com.ifba.endereco.repository.EnderecoIRepository;
import br.com.ifba.estoque.entity.Estoque;
import br.com.ifba.estoque.repository.EstoqueIRepository;
import br.com.ifba.infraestructure.exception.BusinessException;
import br.com.ifba.itempedido.dto.ItemPedidoRequestDto;
import br.com.ifba.itempedido.entity.ItemPedido;
import br.com.ifba.pedido.dto.PedidoPostRequestDto;
import br.com.ifba.pedido.entity.Pedido;
import br.com.ifba.pedido.entity.StatusPedido;
import br.com.ifba.pedido.repository.PedidoIRepository;
import br.com.ifba.produto.entity.Produto;
import br.com.ifba.produto.repository.ProdutoIRepository;
import br.com.ifba.usuario.repository.UsuarioIRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

@Service
public class PedidoService implements PedidoIService {

    public static final Logger logger = Logger.getLogger(PedidoService.class.getName());

    @Autowired
    private PedidoIRepository pedidoIRepository;

    @Autowired
    private UsuarioIRepository usuarioIRepository;

    @Autowired
    private EstoqueIRepository estoqueIRepository;

    @Autowired
    private ProdutoIRepository produtoIRepository;

    @Autowired
    private EnderecoIRepository enderecoIRepository;



    @Override
    @Transactional
    public Pedido save(PedidoPostRequestDto dto) {
        logger.info("Iniciando o Registro do Pedido");
        // o codigo abaixo praticamente é a parte do checkout do fluxo do e-commerce

        Pedido pedido = new Pedido();
        pedido.setNumeroPedido(UUID.randomUUID().toString());
        pedido.setStatus(StatusPedido.PENDENTE);

        pedido.setUsuario(usuarioIRepository.getReferenceById(dto.getUsuario_id()));

        Endereco endereco = enderecoIRepository.findById(dto.getIdEndereco())
                .orElseThrow(() -> new BusinessException("Endereço da entrega não encontrado"));
        pedido.setEndereco(endereco);


        List<ItemPedido> itensDoPedido = new ArrayList<>();
        BigDecimal valorTotal =  BigDecimal.ZERO;

        for (ItemPedidoRequestDto itemDto : dto.getItensPedidos()) {

            Produto produto = produtoIRepository.findById(itemDto.getProdutoId())
                    .orElseThrow(() -> new BusinessException("Produto não encontrado"));

            Estoque estoque = estoqueIRepository.findByProdutoId(produto.getId())
                    .orElseThrow(() -> new BusinessException("Estoque não configurado para o produto"));

            //verifica se há estoque suficiente para o pedido
            if (estoque.getQuantidade() < itemDto.getQuantidade()) {
                throw new BusinessException("Quantidade de produtos insuficiente no estoque");
            }

            //subtrair do estoque e atualizar
            estoque.setQuantidade(estoque.getQuantidade() - itemDto.getQuantidade());
            estoqueIRepository.save(estoque);

            //instanciar o ItemPedido

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            itemPedido.setQuantidade(itemDto.getQuantidade());
            itemPedido.setValorUnitario(produto.getPreco());

            // Calcula o subtotal deste item e soma ao total do pedido
            BigDecimal subtotal = produto.getPreco().multiply(new BigDecimal(itemDto.getQuantidade()));
            valorTotal = valorTotal.add(subtotal);

            itensDoPedido.add(itemPedido);
         }

        //finaliza a montagem do pedido

        pedido.setItensPedido(itensDoPedido);
        pedido.setValorTotal(valorTotal);

        //guarda na base de dados


        return pedidoIRepository.save(pedido);
    }


    @Override
    @Transactional
    public Pedido atualizarStatus(Long id, StatusPedido novoStatus) {
        logger.info("Atualizando status do pedido ID " + id + " para " + novoStatus);

        Pedido pedido = pedidoIRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Pedido não encontrado"));

        //se o pedido já estiver cancelado, não pode mudar o status.
        if (pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new BusinessException("Não é possível alterar o status de um pedido cancelado.");
        }

        pedido.setStatus(novoStatus);
        return pedidoIRepository.save(pedido);
    }


    @Override
    @Transactional
    public Pedido cancelarPedido(Long id) {

        Pedido pedido = pedidoIRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Pedido não encontrado para cancelamento."));

        if (pedido.getStatus() == StatusPedido.CANCELADO) {
            throw new BusinessException("Este pedido já se encontra cancelado.");
        }

        for (ItemPedido item : pedido.getItensPedido()) {
            Estoque estoque = estoqueIRepository.findByProdutoId(item.getProduto().getId())
                    .orElseThrow(() -> new BusinessException("Estoque não encontrado para o produto ID: " + item.getProduto().getId()));

            estoque.setQuantidade(estoque.getQuantidade() + item.getQuantidade());

            estoqueIRepository.save(estoque);
        }

        pedido.setStatus(StatusPedido.CANCELADO);

        return pedidoIRepository.save(pedido);
    }

    @Override
    public Pedido update(Long id, Pedido pedido) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Page<Pedido> findAll(Pageable pageable) {
        return pedidoIRepository.findAll(pageable);
    }

    @Override
    public Pedido findById(Long id) {
        return pedidoIRepository.findById(id).orElseThrow(() -> new BusinessException("not found"));
    }

    @Override
    public Pedido findByNumeroPedido(String numeroPedido) {
        return null;
    }

    @Override
    public Page<Pedido> findByUsuarioID(Long id, Pageable pageable) { return pedidoIRepository.findByUsuarioId(id,pageable);}
}
