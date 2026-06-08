package br.com.ifba.usuario.controller;

import br.com.ifba.usuario.dto.UsuarioGetResponseDto;
import br.com.ifba.usuario.dto.UsuarioPostRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface UsuarioIcontroller {

    ResponseEntity<?> save(UsuarioPostRequestDto usuarioPostRequestDto);
    ResponseEntity<Page<UsuarioGetResponseDto>> findAll(Pageable pageable);
    ResponseEntity<?> update(Long id, UsuarioPostRequestDto usuarioPostRequestDto);
    ResponseEntity<?> delete(Long id);
}
