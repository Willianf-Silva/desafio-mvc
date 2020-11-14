package com.gft.desafiomvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gft")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gft {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Cep é obrigatório.")
	@Size(min = 8, max = 8, message = "Informe somente os números do cep.")
	private String cep;
	
	@NotBlank(message = "Cidade é obrigatório.")
	@Size(max = 50, message = "A cidade não pode conter mais de 50 caracteres.")
	private String cidade;
	
	@NotBlank(message = "Endereço é obrigatório.")
	@Size(max = 50, message = "O endereço não pode conter mais de 50 caracteres.")
	private String endereco;
	
	@NotBlank(message = "Estado é obrigatório.")
	@Size(max = 50, message = "O estado não pode conter mais de 50 caracteres.")
	private String estado;

	@NotBlank(message = "Nome é obrigatório.")
	@Size(max = 50, message = "O nome não pode conter mais de 50 caracteres.")
	@Column(unique = true)
	private String nome;
	
	@NotBlank(message = "Telefone é obrigatório.")
	@Size(max = 50, message = "O estado não pode conter mais de 50 caracteres.")
	private String telefone;
}