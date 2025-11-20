package br.edu.ufcg.voluntarios.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "solicitacao_afiliacao")
public class SolicitacaoAfilicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "entidade_id", nullable = false)
    private Entidade entidade;

    @Enumerated(EnumType.STRING)
    private StatusSolicitacao status = StatusSolicitacao.AGUARDANDO_VALIDACAO;

    // Aceite de termos, etc.
}