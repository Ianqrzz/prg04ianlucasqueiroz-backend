package br.com.ifba.usuario.controller;


import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.service.UsuarioIService;
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

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> save(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioIService.save(usuario));
    }

    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioIService.findAll());
    }

    @PutMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioIService.update(usuario));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioIService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/findbyemail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> findByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(usuarioIService.findByEmail(email));
    }
}
