package br.edu.ufcg.voluntarios.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "voluntario")
public class Voluntario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "entidade_id", nullable = false)
    private Entidade entidade;

    // Campos específicos de voluntário aprovado
}