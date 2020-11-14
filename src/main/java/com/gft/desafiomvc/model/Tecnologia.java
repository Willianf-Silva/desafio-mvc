package com.gft.desafiomvc.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tecnologia")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tecnologia {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Nome da tecnologia é obrigatório.")
	@Size(max = 50, message = "O nome não pode conter mais de 50 caracteres.")
	@Column(unique = true)
	private String nome;
	
	@Size(max = 200, message = "O local da imagem não pode conter mais de 200 caracteres.")
	private String localImagem;
	
	@ManyToMany(mappedBy = "tecnologias")
	private List<Vaga> vagas;
	
	@ManyToMany(mappedBy = "tecnologias")
	private List<Funcionario> funcionarios;	
}
