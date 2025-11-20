package br.edu.ufcg.voluntarios.repositories;

import br.edu.ufcg.voluntarios.entities.SolicitacaoAfilicao;
import br.edu.ufcg.voluntarios.entities.StatusSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoAfilicaoRepository extends JpaRepository<SolicitacaoAfilicao, Long> {
    List<SolicitacaoAfilicao> findByStatus(StatusSolicitacao status);
}