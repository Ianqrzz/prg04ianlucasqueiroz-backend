package br.com.ifba.usuario.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioLoginRequestDto {

    @JsonProperty(value = "email")
    @NotNull
    @NotBlank
    @Email
    private String email;

    @JsonProperty(value = "senha")
    @NotNull
    @NotBlank
    private String senha;
}