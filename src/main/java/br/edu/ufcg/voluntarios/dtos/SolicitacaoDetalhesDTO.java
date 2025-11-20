package br.edu.ufcg.voluntarios.dtos;

import br.edu.ufcg.voluntarios.entities.Entidade;
import br.edu.ufcg.voluntarios.entities.Perfil;
import lombok.Data;

@Data
public class SolicitacaoDetalhesDTO {
    private Entidade entidade;
    private Perfil perfil;
    // Status, etc.
}