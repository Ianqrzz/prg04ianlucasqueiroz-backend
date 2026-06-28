package br.com.ifba.perfilusuario.dto;

import lombok.Data;

import java.util.List;

@Data
public class PerfilUsuarioGetResponseDto {

    private Long id;
    private String nome;
    private String descricao;
    private List<String> permissoes;
}