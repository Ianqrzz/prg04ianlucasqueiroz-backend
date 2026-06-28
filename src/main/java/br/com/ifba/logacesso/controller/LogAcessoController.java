package br.com.ifba.logacesso.controller;

import br.com.ifba.infraestructure.mapper.ObjectMapperUtil;
import br.com.ifba.logacesso.dto.LogAcessoGetResponseDto;
import br.com.ifba.logacesso.dto.LogAcessoPostRequestDto;
import br.com.ifba.logacesso.entity.LogAcesso;
import br.com.ifba.logacesso.service.LogAcessoIService;
import br.com.ifba.usuario.entity.Usuario;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/logacesso")
@RequiredArgsConstructor
public class LogAcessoController implements LogAcessoIController {

    private final LogAcessoIService logAcessoIService;
    private final ObjectMapperUtil objectMapperUtil;

    @Override
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid LogAcessoPostRequestDto logAcessoPost) {
        LogAcesso logAcesso = objectMapperUtil.map(logAcessoPost, LogAcesso.class);

        Usuario usuario = new Usuario();
        usuario.setId(logAcessoPost.getUsuarioId());
        logAcesso.setUsuario(usuario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(logAcessoIService.save(logAcesso), LogAcessoGetResponseDto.class));
    }

    @Override
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<LogAcessoGetResponseDto>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(logAcessoIService.findAll(pageable)
                        .map(log -> objectMapperUtil.map(log, LogAcessoGetResponseDto.class)));
    }

    @Override
    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid LogAcessoPostRequestDto logAcessoPost) {
        LogAcesso logAcesso = objectMapperUtil.map(logAcessoPost, LogAcesso.class);

        Usuario usuario = new Usuario();
        usuario.setId(logAcessoPost.getUsuarioId());
        logAcesso.setUsuario(usuario);

        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.map(logAcessoIService.update(id, logAcesso), LogAcessoGetResponseDto.class));
    }

    @Override
    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        logAcessoIService.delete(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body("Log de Acesso deletado com sucesso");
    }
}