package com.celiosato.exames.dto;

import java.io.Serializable;

public class UsuarioNewDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String nomeCompleto;
	private String cpf;
	
	private String senha;
	private String email;
	private String telefone;
	private Integer tipo;
	
	public UsuarioNewDTO() {
		
	}

	public UsuarioNewDTO(String nomeCompleto, String cpf, String senha, String email, String telefone, Integer tipo) {
		super();
		this.nomeCompleto = nomeCompleto;
		this.cpf = cpf;
		this.senha = senha;
		this.email = email;
		this.telefone = telefone;
		this.tipo = tipo;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
}
