package br.edu.ufcg.voluntarios.services;

import br.edu.ufcg.voluntarios.entities.*;
import br.edu.ufcg.voluntarios.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ServicoAprovacaoTest {

    @Autowired
    private ServicoAprovacao servicoAprovacao;

    @Autowired
    private EntidadeRepository entidadeRepository;

    @Autowired
    private SolicitacaoAfilicaoRepository solicitacaoRepository;

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @Test
    public void testAprovarPF() {
        Entidade entidade = new Entidade();
        entidade.setTipoEntidade("PF");
        entidade.setEmail("test@test.com");
        entidade = entidadeRepository.save(entidade);

        SolicitacaoAfilicao solicitacao = new SolicitacaoAfilicao();
        solicitacao.setEntidade(entidade);
        solicitacao = solicitacaoRepository.save(solicitacao);

        servicoAprovacao.aprovar(solicitacao.getId());

        SolicitacaoAfilicao atualizada = solicitacaoRepository.findById(solicitacao.getId()).get();
        assertEquals(StatusSolicitacao.APROVADO, atualizada.getStatus());

        assertEquals(1, voluntarioRepository.count());
    }
}