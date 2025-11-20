package br.edu.ufcg.voluntarios.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "campanha")
public class Campanha {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String interessesNecessarios; // Ex: "Educação, Meio Ambiente"

    @ManyToOne
    private Ong ong;
}