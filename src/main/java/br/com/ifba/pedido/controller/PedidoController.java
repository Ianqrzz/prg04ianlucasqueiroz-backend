package br.com.ifba.pedido.controller;

import br.com.ifba.infraestructure.mapper.ObjectMapperUtil;
import br.com.ifba.pedido.dto.PedidoGetResponseDto;
import br.com.ifba.pedido.dto.PedidoPostRequestDto;
import br.com.ifba.pedido.entity.Pedido;
import br.com.ifba.pedido.service.PedidoIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoIService pedidoIService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PedidoGetResponseDto> save(@RequestBody @Valid PedidoPostRequestDto pedidoPost) {

        Pedido pedidoSalvo = pedidoIService.save(pedidoPost);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(pedidoSalvo, PedidoGetResponseDto.class));
    }

    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PedidoGetResponseDto>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pedidoIService.findAll(pageable)
                        .map(p -> objectMapperUtil.map(p, PedidoGetResponseDto.class)));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PedidoGetResponseDto> findById(@PathVariable Long id) {
        Pedido pedido = pedidoIService.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.map(pedido, PedidoGetResponseDto.class));
    }

    @GetMapping(path = "/usuario/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PedidoGetResponseDto>> findByUsuarioId(@PathVariable Long usuarioId, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pedidoIService.findByUsuarioID(usuarioId, pageable)
                        .map(p -> objectMapperUtil.map(p, PedidoGetResponseDto.class)));
    }
}