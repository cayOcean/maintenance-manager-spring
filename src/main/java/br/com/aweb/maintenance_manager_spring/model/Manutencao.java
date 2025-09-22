package br.com.aweb.maintenance_manager_spring.model;

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

@Entity // Mapeia a classe para uma tabela no banco de dados
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Manutencao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Identificador único da solicitação

    @NotBlank
    @Size(min = 3, max = 100)
    @Column(nullable = false, length = 100)
    private String solicitante; // Nome de quem solicitou a manutenção

    @NotBlank
    @Size(min = 5, max = 255)
    @Column(nullable = false, length = 255)
    private String descricaoProblema; // Descrição do problema que gerou a solicitação

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(nullable = false, length = 100)
    private String item; // Item que precisa de manutenção (ex: "Impressora HP 2130")

    @NotNull
    @DateTimeFormat(iso = ISO.DATE_TIME)
    @Column(nullable = false, updatable = false)
    private LocalDateTime dataHoraSolicitacao = LocalDateTime.now(); // Data e hora da abertura

    @DateTimeFormat(iso = ISO.DATE_TIME)
    @Column(nullable = true)
    private LocalDateTime dataHoraConclusao; // Quando o técnico finalizou a manutenção
}
