package br.com.aweb.maintenance_manager_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.aweb.maintenance_manager_spring.model.Manutencao;

@Repository
public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {
}