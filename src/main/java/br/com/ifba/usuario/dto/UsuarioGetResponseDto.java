package br.com.ifba.usuario.dto;


import br.com.ifba.perfilusuario.dto.PerfilUsuarioGetResponseDto;
import br.com.ifba.usuario.service.UsuarioIService;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioGetResponseDto {


    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "nome")
    private String nome;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "endereco")
    private String endereco;

    @JsonProperty(value = "perfil_usuario")
    private PerfilUsuarioGetResponseDto perfilUsuario;
}
