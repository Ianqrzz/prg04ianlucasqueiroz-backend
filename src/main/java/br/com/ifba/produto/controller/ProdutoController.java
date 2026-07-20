package br.com.ifba.produto.controller;


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


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(produtoIService.save(produto, produtoPost.getCategoriaId()), ProdutoGetResponseDto.class));
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
    public ResponseEntity<ProdutoGetResponseDto> update(@PathVariable Long id, @RequestBody @Valid ProdutoPostRequestDto produtoPost) {

        Produto dadosAtualizacao = objectMapperUtil.map(produtoPost, Produto.class);

        // Passamos o ID do produto, a entidade com os novos dados, e o ID da nova categoria
        Produto produtoSalvo = produtoIService.update(id, dadosAtualizacao, produtoPost.getCategoriaId());

        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.map(produtoSalvo, ProdutoGetResponseDto.class));
    }

    @Override
    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable Long id) {
        produtoIService.delete(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Produto deletado com sucesso");
    }


    @GetMapping(path = "findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?>  findById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.map(produtoIService.findById(id), ProdutoGetResponseDto.class));
    }
}