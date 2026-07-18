package br.com.ifba.endereco.dto;


import br.com.ifba.usuario.dto.UsuarioGetResponseDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoGetResponseDto {


    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "Usuario")
    private UsuarioGetResponseDto usuario;

    @JsonProperty(value = "rua")
    private String rua;

    @JsonProperty(value = "numero")
    private String numero;

    @JsonProperty(value = "bairro")
    private String bairro;

    @JsonProperty(value = "cidade")
    private String cidade;

    @JsonProperty(value = "estado")
    private String estado;

    @JsonProperty(value = "cep")
    private String cep;
}
