package br.com.ifba.perfilusuario.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class PerfilUsuarioPostRequestDto {

    @NotNull(message = "O nome não pode ser nulo")
    @NotBlank(message = "O nome não pode estar vazio")
    private String nome;

    private String descricao;

    private List<String> permissoes;
}