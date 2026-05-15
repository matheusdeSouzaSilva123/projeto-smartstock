package br.com.smartstock.api.entities;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_funcionario")
public class Funcionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Pattern(regexp = "^[\\p{L}] + ([\\p{L}]+)*$", message = "O nome do funcionário deve conter apenas letras e espaços.")
	@NotBlank(message ="O nome é obrigatório.")
	private String nome;
	
	@NotBlank(message = "O CPF é obrigatório")
    @CPF(message = "CPF inválido") 
    @Size(min = 11, max = 14) 
    @Column(name = "cpf", unique = true, nullable = false)
	private String cpf;
	
	@NotBlank(message = "O RG não pode estar em branco")
    @Pattern(regexp = "^[0-9.a-zA-Z-]*$", message = "RG com formato inválido")
    @Column(name = "rg", length = 20)
	private String rg;
		
    @Email(message = "E-mail inválido.")
    @Size(max = 120, message = "E-mail deve ter no máximo 100 caracteres.")
    @Column(unique = true, length = 100)
    private String email;
    
    @Column(nullable = false)
    private String cargo;
    
	public Funcionario() {}
	
	public Funcionario (String email, String nome, String cargo, String rg, String cpf) {
		this.email = email;
		this.nome = nome;
		this.cargo = cargo;
		this.rg = rg;
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}


	
	
	
}