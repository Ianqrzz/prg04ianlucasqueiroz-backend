package br.com.ifba.perfilusuario.controller;

import br.com.ifba.infraestructure.mapper.ObjectMapperUtil;
import br.com.ifba.perfilusuario.dto.PerfilUsuarioGetResponseDto;
import br.com.ifba.perfilusuario.dto.PerfilUsuarioPostRequestDto;
import br.com.ifba.perfilusuario.entity.PerfilUsuario;
import br.com.ifba.perfilusuario.service.PerfilUsuarioIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfilusuario")
@RequiredArgsConstructor
public class PerfilUsuarioController implements PerfilUsuarioIController {

    private final PerfilUsuarioIService perfilUsuarioIService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid PerfilUsuarioPostRequestDto perfilUsuarioPostRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(perfilUsuarioIService.save(
                                objectMapperUtil.map(perfilUsuarioPostRequestDto, PerfilUsuario.class)),
                        PerfilUsuarioGetResponseDto.class));
    }

    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PerfilUsuarioGetResponseDto>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.perfilUsuarioIService.findAll(pageable)
                        .map(perfilUsuario -> objectMapperUtil.map(perfilUsuario, PerfilUsuarioGetResponseDto.class)));
    }

    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody @Valid PerfilUsuarioPostRequestDto perfilUsuarioPostRequestDto) {
        perfilUsuarioIService.update(id, objectMapperUtil.map(perfilUsuarioPostRequestDto, PerfilUsuario.class));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        perfilUsuarioIService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Perfil de Usuário deletado com sucesso");
    }
}