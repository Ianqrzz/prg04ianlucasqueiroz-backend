package br.com.ifba.usuario.controller;


import br.com.ifba.infraestructure.mapper.ObjectMapperUtil;
import br.com.ifba.perfilusuario.entity.PerfilUsuario;
import br.com.ifba.usuario.dto.UsuarioGetResponseDto;
import br.com.ifba.usuario.dto.UsuarioPostRequestDto;
import br.com.ifba.usuario.entity.Usuario;
import br.com.ifba.usuario.service.UsuarioIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController implements UsuarioIcontroller{

    private final UsuarioIService usuarioIService;
    private final ObjectMapperUtil objectMapperUtil;

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody @Valid UsuarioPostRequestDto usuariopost) {
        Usuario usuario = objectMapperUtil.map(usuariopost, Usuario.class);

        PerfilUsuario perfilUsuario = new PerfilUsuario();
        perfilUsuario.setId(usuariopost.getPerfilUsuarioId());
        usuario.setPerfilUsuario(perfilUsuario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(objectMapperUtil.map(usuarioIService.save(usuario), UsuarioGetResponseDto.class));
    }




    @GetMapping(path = "/findall", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<UsuarioGetResponseDto>> findAll(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.usuarioIService.findAll(pageable).map(c -> objectMapperUtil.
                        map(c, UsuarioGetResponseDto.class)));
    }



    @PutMapping(path = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid UsuarioPostRequestDto usuarioPostResquestDto) {
        Usuario usuario = objectMapperUtil.map(usuarioPostResquestDto, Usuario.class);

        PerfilUsuario perfilUsuario = new PerfilUsuario();
        perfilUsuario.setId(usuarioPostResquestDto.getPerfilUsuarioId());
        usuario.setPerfilUsuario(perfilUsuario);

        usuarioIService.update(id, usuario);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        usuarioIService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT)
                .body("Usuário deletado com sucesso");
    }

    @GetMapping(path = "/findbyemail/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioGetResponseDto> findByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.map(usuarioIService.findByEmail(email), UsuarioGetResponseDto.class));
    }

    @PostMapping(path = "/validarLogin", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioGetResponseDto> validarLogin(@RequestBody UsuarioPostRequestDto dto) {
        Usuario usuario = usuarioIService.validarLogin(dto.getEmail(), dto.getSenha());
        return ResponseEntity.status(HttpStatus.OK)
                .body(objectMapperUtil.map(usuario, UsuarioGetResponseDto.class));
    }
}