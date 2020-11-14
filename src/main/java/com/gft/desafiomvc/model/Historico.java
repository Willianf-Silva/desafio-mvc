package com.gft.desafiomvc.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Historico {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Gft é obrigatório.")
	@Size(max = 60, message = "A Gft não pode conter mais de 60 caracteres.")
	private String gft;
	
	@NotBlank(message = "Funcionário é obrigatório.")
	@Size(max = 60, message = "O funcionario não pode conter mais de 60 caracteres.")
	private String funcionario;
	
	@NotBlank(message = "Cliente é obrigatório.")
	@Size(max = 60, message = "O cliente não pode conter mais de 60 caracteres.")
	private String cliente;
	
	@NotBlank(message = "codigo da vaga é obrigatório.")
	@Size(max = 20, message = "O codigo não pode conter mais de 20 caracteres.")
	private String codigoVaga;
	
	@NotNull(message = "Data de inicio wa é obrigatório.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")	// Definindo o formato da data
	private LocalDate inicioWa;
	
	@NotNull(message = "Data de alocação é obrigatório.")
	@DateTimeFormat(pattern = "dd/MM/yyyy")	// Definindo o formato da data
	private LocalDate inicioAlocacao;

}
