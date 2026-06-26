package br.com.ifba.produto.controller;

import br.com.ifba.produto.dto.ProdutoGetResponseDto;
import br.com.ifba.produto.dto.ProdutoPostRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface ProdutoIController {
    ResponseEntity<?> save(ProdutoPostRequestDto produtoPostRequestDto);
    ResponseEntity<Page<ProdutoGetResponseDto>> findAll(Pageable pageable);
    ResponseEntity<?> update(@PathVariable Long id, ProdutoPostRequestDto produtoPostRequestDto);
    ResponseEntity<?> delete(@PathVariable Long id);
}
