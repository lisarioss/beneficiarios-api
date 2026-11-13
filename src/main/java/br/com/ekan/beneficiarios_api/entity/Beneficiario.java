package br.com.ekan.beneficiarios_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "beneficiario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Beneficiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String telefone;

    private LocalDate dataNascimento;

    private LocalDateTime dataInclusao;

    private LocalDateTime dataAtualizacao;

    @OneToMany(
        mappedBy = "beneficiario",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Documento> documentos = new ArrayList<>();

}