package br.com.ifba.estoque.controller;

import br.com.ifba.estoque.dto.EstoqueGetResponseDto;
import br.com.ifba.estoque.dto.EstoquePostRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface EstoqueIController {
    ResponseEntity<?> save(EstoquePostRequestDto estoquePostRequestDto);
    ResponseEntity<Page<EstoqueGetResponseDto>> findAll(Pageable pageable);
    ResponseEntity<?> update(@PathVariable Long id, EstoquePostRequestDto estoquePostRequestDto);
    ResponseEntity<?> delete(@PathVariable Long id);
}
