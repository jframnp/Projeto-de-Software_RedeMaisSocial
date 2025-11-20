package br.edu.ufcg.voluntarios.controllers;

import br.edu.ufcg.voluntarios.dtos.*;
import br.edu.ufcg.voluntarios.entities.Entidade;
import br.edu.ufcg.voluntarios.services.ServicoAfilicao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/afiliacao")
public class AfilicaoController {

    @Autowired
    private ServicoAfilicao servicoAfilicao;

    @PostMapping("/etapa1")
    public ResponseEntity<String> etapa1(@RequestBody DadosBasicosDTO dto) {
        servicoAfilicao.verificarDadosBasicos(dto);
        return ResponseEntity.ok("Dados básicos validados. Prossiga para etapa 2.");
    }

    @PostMapping("/etapa2")
    public ResponseEntity<Long> etapa2(@RequestBody DetalhesEntidadeDTO detalhes, @RequestParam String basicosJson) {
    try {
        ObjectMapper mapper = new ObjectMapper();
        DadosBasicosDTO basicos = mapper.readValue(basicosJson, DadosBasicosDTO.class); // Converte string JSON para DTO
        Entidade entidade = servicoAfilicao.criarEntidade(basicos, detalhes);
        return ResponseEntity.ok(entidade.getId());
    } catch (Exception e) {
        return ResponseEntity.badRequest().body(0L); // Erro se JSON inválido
    }
    }

    @PostMapping("/etapa3")
    public ResponseEntity<String> etapa3(@RequestParam Long entidadeId, @RequestBody PerfilDTO dto) {
        servicoAfilicao.salvarPerfil(entidadeId, dto);
        return ResponseEntity.ok("Perfil salvo. Prossiga para etapa 4.");
    }

    @PostMapping("/etapa4")
    public ResponseEntity<String> etapa4(@RequestParam Long entidadeId, @RequestBody FinalizarDTO dto) {
        servicoAfilicao.finalizarCadastro(entidadeId, dto);
        return ResponseEntity.ok("Cadastro finalizado. Aguardando aprovação.");
    }
}