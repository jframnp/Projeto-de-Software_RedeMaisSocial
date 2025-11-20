package br.edu.ufcg.voluntarios.repositories;

import br.edu.ufcg.voluntarios.entities.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
}