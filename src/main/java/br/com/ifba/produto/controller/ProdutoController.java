package br.com.ifba.produto.controller;


import br.com.ifba.categoria.entity.Categoria;
import br.com.ifba.infraestructure.mapper.ObjectMapperUtil;
import br.com.ifba.produto.dto.ProdutoGetResponseDto;
import br.com.ifba.produto.dto.ProdutoPostRequestDto;
import br.com.ifba.produto.entity.Produto;
import br.com.ifba.produto.service.ProdutoIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
@RequiredArgsConstructor
public class ProdutoController implements ProdutoIController {

    private final ProdutoIService produtoIService;
    private final ObjectMapperUtil objectMapperUtil;

    @Override
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid ProdutoPostRequestDto produtoPost) {
        Produto produto = objectMapperUtil.map(produtoPost, Produto.class);

        Categoria categoria = new Categoria();
        categoria.setId(produtoPost.getCategoriaId());
        produto.setCategoria(categoria);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(produtoIService.save(produto), ProdutoGetResponseDto.class));
    }

    @Override
    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ProdutoGetResponseDto>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(produtoIService.findAll(pageable)
                        .map(c -> objectMapperUtil.map(c, ProdutoGetResponseDto.class)));
    }

    @Override
    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid ProdutoPostRequestDto produtoPost) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.map(produtoIService.update(
                                id, objectMapperUtil.map(produtoPost, Produto.class)),
                        ProdutoGetResponseDto.class));
    }

    @Override
    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        produtoIService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Produto deletado com sucesso");
    }
}