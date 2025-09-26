package sistema_manutencao_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema_manutencao_spring.model.Manutencao;

public interface ManutencaoRepository extends JpaRepository<Manutencao, Long> {

}
