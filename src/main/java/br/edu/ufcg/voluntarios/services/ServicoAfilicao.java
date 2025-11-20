package br.edu.ufcg.voluntarios.services;

import br.edu.ufcg.voluntarios.dtos.*;
import br.edu.ufcg.voluntarios.entities.*;
import br.edu.ufcg.voluntarios.exceptions.EntidadeDuplicadaException;
import br.edu.ufcg.voluntarios.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoAfilicao {

    @Autowired
    private EntidadeRepository entidadeRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private SolicitacaoAfilicaoRepository solicitacaoRepository;

    // Etapa 1: Verificar duplicidade
    public void verificarDadosBasicos(DadosBasicosDTO dto) {
        if (entidadeRepository.existsByEmail(dto.getEmail())) {
            throw new EntidadeDuplicadaException("Email duplicado");
        }
        if (entidadeRepository.existsByDocumento(dto.getDocumento())) {
            throw new EntidadeDuplicadaException("Documento duplicado");
        }
        // Retorna OK implicitamente
    }

    // Etapa 2: Criar Entidade (criação implícita via save)
    public Entidade criarEntidade(DadosBasicosDTO basicos, DetalhesEntidadeDTO detalhes) {
        Entidade entidade = new Entidade();
        entidade.setTipoEntidade(basicos.getTipoEntidade());
        entidade.setEmail(basicos.getEmail());
        entidade.setDocumento(basicos.getDocumento());
        entidade.setNome(detalhes.getNome());
        entidade.setSenha(detalhes.getSenha()); // Hash em prod
        return entidadeRepository.save(entidade); // Instância "nasce" aqui
    }

    // Etapa 3: Salvar Perfil (ligado à Entidade)
    public void salvarPerfil(Long entidadeId, PerfilDTO dto) {
        Entidade entidade = entidadeRepository.findById(entidadeId)
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada"));
        Perfil perfil = new Perfil();
        perfil.setEntidade(entidade);
        perfil.setHabilidades(dto.getHabilidades());
        perfil.setInteresses(dto.getInteresses());
        perfilRepository.save(perfil); // Instância "nasce" aqui
    }

    // Etapa 4: Finalizar (criar Solicitacao, enviar email simulado)
    public void finalizarCadastro(Long entidadeId, FinalizarDTO dto) {
        if (!dto.isAceitaTermos()) {
            throw new RuntimeException("Termos não aceitos");
        }
        Entidade entidade = entidadeRepository.findById(entidadeId)
                .orElseThrow(() -> new RuntimeException("Entidade não encontrada"));
        SolicitacaoAfilicao solicitacao = new SolicitacaoAfilicao();
        solicitacao.setEntidade(entidade);
        solicitacaoRepository.save(solicitacao); // Instância "nasce" aqui

        // Simular NotificadorEmail
        System.out.println("Email enviado para: " + entidade.getEmail() + " - Cadastro finalizado, aguardando aprovação.");
    }
}