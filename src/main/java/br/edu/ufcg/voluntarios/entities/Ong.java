package br.edu.ufcg.voluntarios.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ong")
public class Ong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "entidade_id", nullable = false)
    private Entidade entidade;

    // Campos específicos de ONG
}