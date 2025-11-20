package br.edu.ufcg.voluntarios.services;

import br.edu.ufcg.voluntarios.dtos.SolicitacaoDetalhesDTO;
import br.edu.ufcg.voluntarios.entities.*;
import br.edu.ufcg.voluntarios.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoAprovacao {

    @Autowired
    private SolicitacaoAfilicaoRepository solicitacaoRepository;

    @Autowired
    private EntidadeRepository entidadeRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @Autowired
    private OngRepository ongRepository;

    // Listar pendentes (mensagem listarSolicitacoesPendentes())
    public List<SolicitacaoAfilicao> listarPendentes() {
        return solicitacaoRepository.findByStatus(StatusSolicitacao.AGUARDANDO_VALIDACAO);
    }

    // Ver detalhes (buscar detalhes, incluindo Entidade e Perfil)
    public SolicitacaoDetalhesDTO verDetalhes(Long solicitacaoId) {
        SolicitacaoAfilicao solicitacao = solicitacaoRepository.findById(solicitacaoId)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));
        SolicitacaoDetalhesDTO dto = new SolicitacaoDetalhesDTO();
        dto.setEntidade(solicitacao.getEntidade());
        // Buscar Perfil via Entidade (como no design)
        Perfil perfil = perfilRepository.findAll().stream()
                .filter(p -> p.getEntidade().getId().equals(solicitacao.getEntidade().getId()))
                .findFirst().orElse(null);
        dto.setPerfil(perfil);
        return dto;
    }

    // Aprovar (mudar status, criar Voluntario/Ong)
    public void aprovar(Long solicitacaoId) {
        SolicitacaoAfilicao solicitacao = solicitacaoRepository.findById(solicitacaoId)
                .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));
        solicitacao.setStatus(StatusSolicitacao.APROVADO);
        solicitacaoRepository.save(solicitacao);

        Entidade entidade = solicitacao.getEntidade();
        if ("PF".equals(entidade.getTipoEntidade())) {
            Voluntario voluntario = new Voluntario();
            voluntario.setEntidade(entidade);
            voluntarioRepository.save(voluntario); // Instância Voluntario "nasce"
        } else if ("PJ".equals(entidade.getTipoEntidade())) {
            Ong ong = new Ong();
            ong.setEntidade(entidade);
            ongRepository.save(ong); // Instância Ong "nasce"
        }

        // Simular email aprovação
        System.out.println("Email enviado para: " + entidade.getEmail() + " - Afiliação aprovada!");
    }
    public List<SolicitacaoAfilicao> listarAprovados() {
    return solicitacaoRepository.findByStatus(StatusSolicitacao.APROVADO);
}
}