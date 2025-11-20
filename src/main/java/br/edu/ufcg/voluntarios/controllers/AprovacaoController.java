package br.edu.ufcg.voluntarios.controllers;

import br.edu.ufcg.voluntarios.dtos.SolicitacaoDetalhesDTO;
import br.edu.ufcg.voluntarios.entities.SolicitacaoAfilicao;
import br.edu.ufcg.voluntarios.services.ServicoAprovacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aprovacao")
public class AprovacaoController {

    @Autowired
    private ServicoAprovacao servicoAprovacao;

    @GetMapping("/pendentes")
    public ResponseEntity<List<SolicitacaoAfilicao>> listarPendentes() {
        return ResponseEntity.ok(servicoAprovacao.listarPendentes());
    }

    @GetMapping("/detalhes/{id}")
    public ResponseEntity<SolicitacaoDetalhesDTO> verDetalhes(@PathVariable Long id) {
        return ResponseEntity.ok(servicoAprovacao.verDetalhes(id));
    }

    @PostMapping("/aprovar/{id}")
    public ResponseEntity<String> aprovar(@PathVariable Long id) {
        servicoAprovacao.aprovar(id);
        return ResponseEntity.ok("Afiliação aprovada com sucesso.");
    }
    @GetMapping("/aprovados")
    public ResponseEntity<List<SolicitacaoAfilicao>> listarAprovados() {
    return ResponseEntity.ok(servicoAprovacao.listarAprovados());
}
}