package br.com.ifba.logacesso.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogAcessoPostRequestDto {

    @JsonProperty(value = "acao")
    @NotNull(message = "A ação é obrigatória")
    @NotBlank(message = "A ação não pode ser vazia")
    private String acao;

    @JsonProperty(value = "ip_origem")
    private String ipOrigem;

    @JsonProperty(value = "usuario_id")
    @NotNull(message = "O usuário é obrigatório")
    private Long usuarioId;
}