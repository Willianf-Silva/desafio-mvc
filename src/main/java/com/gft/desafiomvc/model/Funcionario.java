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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "funcionario")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@NotBlank(message = "Cargo é obrigatório.")
	@Size(max = 60, message = "O cargo não pode conter mais de 60 caracteres.")
	private String cargo;
	
	@NotNull(message = "Data de inicio wa é obrigatório.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")	// Definindo o formato da data	
	private LocalDate inicioWa;
	
	@NotBlank(message = "Matricula é obrigatório")
	@Size(max = 20, message = "A matricula não pode conter mais de 20 caracteres.")
	@Column(unique = true)
	private String matricula;
	
	@NotBlank(message = "Nome é obrigatório")
	@Size(max = 60, message = "O nome não pode conter mais de 60 caracteres.")
	private String nome;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")	// Definindo o formato da data	
	private LocalDate terminoWa;

	@ToString.Exclude
	@Valid
	@ManyToOne
	@JoinColumn(name = "gft_id")	
	private Gft gft;
	
	@Transient
	private Long gftIdTransient;
	
	@ToString.Exclude
	@ManyToOne
	@JoinColumn(name = "vaga_id")
	private Vaga vaga;

	@ToString.Exclude
	@Valid
	@ManyToMany
	@JoinTable(
			name = "funcionario_tecnologia",
			joinColumns = {@JoinColumn(referencedColumnName = "id")}, 
			inverseJoinColumns = {@JoinColumn(referencedColumnName = "id")})
	private List<Tecnologia> tecnologias;
}
