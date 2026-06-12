package br.com.ifba.usuario.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioPostRequestDto {


   @JsonProperty(value = "nome")
   @NotNull(message = "O nome é obrigatorio")
   @NotBlank(message = "O nome não pode ser vazio")
   @Size(min = 3, max = 100)
    private  String nome;

   @JsonProperty(value = "email")
   @NotNull
   @NotBlank
   @Email
    private  String email;


   @JsonProperty(value = "senha")
   @NotNull(message = " A senha é obrigatorio")
   @NotBlank(message = " A senha não pode ser vazia")
   @Size(min = 6)
    private  String senha;


   @JsonProperty(value = "endereco")
    private  String endereco;


}
