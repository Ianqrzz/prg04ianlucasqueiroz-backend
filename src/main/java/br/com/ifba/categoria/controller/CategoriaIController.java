package br.com.ifba.categoria.controller;

import br.com.ifba.categoria.dto.CategoriaGetResponseDto;
import br.com.ifba.categoria.dto.CategoriaPostRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CategoriaIController {

    ResponseEntity<?> save(CategoriaPostRequestDto categoriaPostRequestDto);
    ResponseEntity<Page<CategoriaGetResponseDto>> findAll(Pageable pageable);
    ResponseEntity<?> update(Long id, CategoriaPostRequestDto categoriaPostRequestDto);
    ResponseEntity<?> delete(Long id);
}
