package br.edu.ufcg.voluntarios.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "entidade")
public class Entidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tipoEntidade; // 'PF' ou 'PJ'

    @Column(unique = true, nullable = false)
    private String email;

    private String senha;
    private String nome; // Nome para PF, Razão Social para PJ
    private String documento; // CPF ou CNPJ

    // Outros campos compartilhados ou específicos (ex: endereço, telefone) podem ser adicionados
}