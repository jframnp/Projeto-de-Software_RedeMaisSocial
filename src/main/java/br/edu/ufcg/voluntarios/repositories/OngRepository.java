package br.edu.ufcg.voluntarios.repositories;

import br.edu.ufcg.voluntarios.entities.Ong;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OngRepository extends JpaRepository<Ong, Long> {
}