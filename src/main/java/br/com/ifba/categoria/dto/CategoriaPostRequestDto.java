package br.com.ifba.categoria.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaPostRequestDto {

    @JsonProperty(value = "nome")
    @NotNull(message = "O nome é obrigatorio")
    @NotBlank(message = "O nome não pode ser vazio")
    @Size(min = 3, max = 100)
    private  String nome;


    @JsonProperty(value = "descricao")
    @NotNull
    @NotBlank
    @Size(min = 3, max = 255)
    private  String descricao;
}
