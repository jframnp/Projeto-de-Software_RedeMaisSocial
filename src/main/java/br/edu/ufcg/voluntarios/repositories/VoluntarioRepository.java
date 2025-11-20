package br.edu.ufcg.voluntarios.repositories;

import br.edu.ufcg.voluntarios.entities.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
}