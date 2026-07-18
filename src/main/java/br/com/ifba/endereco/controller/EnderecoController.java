package br.com.ifba.endereco.controller;

import br.com.ifba.endereco.dto.EnderecoGetResponseDto;
import br.com.ifba.endereco.dto.EnderecoPostRequestDto;
import br.com.ifba.endereco.entity.Endereco;
import br.com.ifba.endereco.service.EnderecoIService;
import br.com.ifba.infraestructure.mapper.ObjectMapperUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/endereco")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoIService enderecoIService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnderecoGetResponseDto> save(@RequestBody @Valid EnderecoPostRequestDto enderecoPost) {

        Endereco endereco = objectMapperUtil.map(enderecoPost, Endereco.class);
        Endereco enderecoSalvo = enderecoIService.save(endereco, enderecoPost.getIdUsuario());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(enderecoSalvo, EnderecoGetResponseDto.class));
    }

    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<EnderecoGetResponseDto>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(enderecoIService.findAll(pageable)
                        .map(e -> objectMapperUtil.map(e, EnderecoGetResponseDto.class)));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnderecoGetResponseDto> findById(@PathVariable Long id) {
        Endereco endereco = enderecoIService.findById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.map(endereco, EnderecoGetResponseDto.class));
    }

    @GetMapping(path = "/usuario/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<EnderecoGetResponseDto>> findByUsuarioId(@PathVariable Long usuarioId, Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(enderecoIService.findByUsuarioId(usuarioId, pageable)
                        .map(e -> objectMapperUtil.map(e, EnderecoGetResponseDto.class)));
    }

    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EnderecoGetResponseDto> update(@PathVariable Long id, @RequestBody @Valid EnderecoPostRequestDto enderecoPost) {

        Endereco dadosAtualizacao = objectMapperUtil.map(enderecoPost, Endereco.class);
        Endereco enderecoAtualizado = enderecoIService.update(id, dadosAtualizacao);

        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.map(enderecoAtualizado, EnderecoGetResponseDto.class));
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        enderecoIService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Endereço deletado com sucesso");
    }
}