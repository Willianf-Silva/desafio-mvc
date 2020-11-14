package com.gft.desafiomvc.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "vaga")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vaga {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message = "Data de abertura da vaga é obrigatório.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")	// Definindo o formato da data	
	private LocalDate aberturaVaga;
	
	@NotBlank(message = "Código da vaga é obrigatório.")
	@Size(max = 20, message = "O codigo não pode conter mais de 20 caracteres.")
	@Column(unique = true)
	private String codigoVaga;
	
	@NotBlank(message = "Descrição da vaga é obrigatório.")
	@Size(max = 200, message = "A descrição da vaga não pode conter mais de 200 caracteres.")
	private String descricaoVaga;
	
	@NotBlank(message = "Projeto é obrigatório.")
	@Size(max = 50, message = "O projeto não pode conter mais de 50 caracteres.")
	private String projeto;
	
	@NotNull(message = "Quantidade de vagas é obrigatório.")
	private int qtdVaga;
	
	@ToString.Exclude
	@NotEmpty(message = "As tecnologias são obrigatórias.")
	@ManyToMany
	@JoinTable(
			name = "vaga_tecnologia",
			joinColumns = {@JoinColumn(referencedColumnName = "id")}, 
			inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
	private List<Tecnologia> tecnologias;
}
