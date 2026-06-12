package br.com.ifba.categoria.controller;

import br.com.ifba.categoria.dto.CategoriaGetResponseDto;
import br.com.ifba.categoria.dto.CategoriaPostRequestDto;
import br.com.ifba.categoria.entity.Categoria;
import br.com.ifba.categoria.service.CategoriaIService;
import br.com.ifba.infraestructure.mapper.ObjectMapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController implements CategoriaIController{

    private final CategoriaIService categoriaIService;
    private final ObjectMapperUtil objectMapperUtil;


    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid CategoriaPostRequestDto categoriaPost)    {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(categoriaIService.save(
                        (objectMapperUtil.map(categoriaPost,Categoria.class))),
                        CategoriaGetResponseDto.class));
    }


    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<CategoriaGetResponseDto>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.categoriaIService.findAll(pageable).map(c -> objectMapperUtil.
                        map(c, CategoriaGetResponseDto.class)));
    }

    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid CategoriaPostRequestDto categoriaPostRequestDto) {
        categoriaIService.update(id,ObjectMapperUtil.map(categoriaPostRequestDto, Categoria.class));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        categoriaIService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
            .body("Categoria deletada com sucesso");
    }
}
