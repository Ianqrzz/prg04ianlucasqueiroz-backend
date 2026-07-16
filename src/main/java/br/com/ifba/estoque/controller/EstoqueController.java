package br.com.ifba.estoque.controller;

import br.com.ifba.estoque.dto.EstoqueGetResponseDto;
import br.com.ifba.estoque.dto.EstoquePostRequestDto;
import br.com.ifba.estoque.entity.Estoque;
import br.com.ifba.estoque.service.EstoqueIService;
import br.com.ifba.infraestructure.mapper.ObjectMapperUtil;
import br.com.ifba.produto.entity.Produto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
@RequiredArgsConstructor
public class EstoqueController implements EstoqueIController{

    private final EstoqueIService estoqueIService;
    private final ObjectMapperUtil objectMapperUtil;


    @Override
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid EstoquePostRequestDto estoquePost) {
        Estoque estoque = objectMapperUtil.map(estoquePost, Estoque.class);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(estoqueIService.save(estoque, estoquePost.getIdProduto()), EstoqueGetResponseDto.class));

    }

    @Override
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<EstoqueGetResponseDto>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(estoqueIService.findAll(pageable)
                        .map(c -> objectMapperUtil.map(c, EstoqueGetResponseDto.class)));
    }

    @Override
    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable Long id, @Valid EstoquePostRequestDto estoquePostRequestDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.map(estoqueIService.update
                        (id, objectMapperUtil.map(estoquePostRequestDto, Estoque.class))
                        , EstoqueGetResponseDto.class));
    }

    @Override
    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        estoqueIService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Estoque deletado com sucesso");
    }
}
