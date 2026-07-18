package br.com.ifba.endereco.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoPostRequestDto {


    @JsonProperty(value = "idUsuario")
    @NotNull
    private Long idUsuario;


    @JsonProperty(value = "rua")
    @NotNull
    @NotBlank
    private String rua;

    @JsonProperty(value = "numero")
    @NotNull
    @NotBlank
    private String numero;

    @JsonProperty(value = "bairro")
    @NotNull
    @NotBlank
    private String bairro;

    @JsonProperty(value = "cidade")
    @NotNull
    @NotBlank
    private String cidade;

    @JsonProperty(value = "estado")
    @NotNull
    @NotBlank
    private String estado;

    @JsonProperty(value = "cep")
    @NotNull
    @NotBlank
    private String cep;


}
