package br.com.ifba.logacesso.controller;

import br.com.ifba.logacesso.dto.LogAcessoGetResponseDto;
import br.com.ifba.logacesso.dto.LogAcessoPostRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface LogAcessoIController {

    ResponseEntity<?> save(LogAcessoPostRequestDto logAcessoPostRequestDto);

    ResponseEntity<Page<LogAcessoGetResponseDto>> findAll(Pageable pageable);

    ResponseEntity<?> update(@PathVariable Long id, LogAcessoPostRequestDto logAcessoPostRequestDto);

    ResponseEntity<?> delete(@PathVariable Long id);
}