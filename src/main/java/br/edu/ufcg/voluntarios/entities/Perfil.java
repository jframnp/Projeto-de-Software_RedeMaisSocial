package br.edu.ufcg.voluntarios.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "perfil")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "entidade_id", nullable = false)
    private Entidade entidade;

    private String habilidades; // Ex: "Java, Voluntariado"
    private String interesses; // Ex: "Educação, Meio Ambiente"
}   