package br.edu.ufcg.voluntarios.repositories;

import br.edu.ufcg.voluntarios.entities.Entidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntidadeRepository extends JpaRepository<Entidade, Long> {
    boolean existsByEmail(String email);
    boolean existsByDocumento(String documento);
}