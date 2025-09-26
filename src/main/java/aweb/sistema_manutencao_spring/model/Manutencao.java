package sistema_manutencao_spring.model;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Manutencao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador da manutenção

    @NotBlank
    @Size(min = 3, max = 100)
    @Column(nullable = false, length = 100)
    private String solicitante; // Quem abriu o chamado

    @NotBlank
    @Size(min = 5, max = 255)
    @Column(nullable = false, length = 255)
    private String descricaoProblema; // Descrição do defeito

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(nullable = false, length = 100)
    private String item; // Equipamento ou item do chamado

    @NotNull
    @DateTimeFormat(iso = ISO.DATE_TIME)
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraSolicitacao = LocalDateTime.now(); // Momento da abertura

    @DateTimeFormat(iso = ISO.DATE_TIME)
    @Column(nullable = true)
    private LocalDateTime dataHoraConclusao; // Momento da finalização
}
