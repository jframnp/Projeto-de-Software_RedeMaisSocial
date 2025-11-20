package br.edu.ufcg.voluntarios.dtos;

import lombok.Data;

@Data
public class DadosBasicosDTO {
    private String tipoEntidade;
    private String email;
    private String documento;
}