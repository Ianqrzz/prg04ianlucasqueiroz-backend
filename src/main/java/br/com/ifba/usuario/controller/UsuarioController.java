package br.com.ifba.usuario.controller;


import br.com.ifba.infraestructure.mapper.ObjectMapperUtil;
import br.com.ifba.usuario.dto.UsuarioGetResponseDto;
import br.com.ifba.usuario.dto.UsuarioPostRequestDto;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.service.UsuarioIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioIService usuarioIService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid UsuarioPostRequestDto usuariopost) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(usuarioIService.save(
                        (objectMapperUtil.map(usuariopost, Usuario.class))),
                        UsuarioGetResponseDto.class));
    }

    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.mapAll(
                        this.usuarioIService.findAll(),
                        UsuarioGetResponseDto.class
                ));
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> update(@RequestBody @Valid Usuario usuario) {
        usuarioIService.update(usuario);
        return new ResponseEntity<>(usuario, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        usuarioIService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
            .body("Usuário deletado com sucesso");
    }

    @GetMapping(path = "/findbyemail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> findByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioIService.findByEmail(email));
    }
}
