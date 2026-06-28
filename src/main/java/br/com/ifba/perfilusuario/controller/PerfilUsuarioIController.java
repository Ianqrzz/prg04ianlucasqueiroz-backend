package br.com.ifba.perfilusuario.controller;

import br.com.ifba.perfilusuario.dto.PerfilUsuarioGetResponseDto;
import br.com.ifba.perfilusuario.dto.PerfilUsuarioPostRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface PerfilUsuarioIController {

    ResponseEntity<?> save(PerfilUsuarioPostRequestDto perfilUsuarioPostRequestDto);

    ResponseEntity<Page<PerfilUsuarioGetResponseDto>> findAll(Pageable pageable);

    ResponseEntity<?> update(@PathVariable Long id, PerfilUsuarioPostRequestDto perfilUsuarioPostRequestDto);

    ResponseEntity<?> delete(@PathVariable Long id);
}