package com.wsb.SicrediChallenge.Model;

import java.util.*;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Check;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "tb_associado")
public class Associado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Size(min = 3, message = "O nome deve ter no minimo 3 letras")
	@NotEmpty(message = "O Nome deve ser preenchido")
	private String nome;

	@Column(name = "cpf", nullable = false, unique = true)
	@NotBlank(message = "CPF obrigatório")
	@CPF(message = "CPF inválido")
	private String cpf;

	private boolean voto;

	private boolean jaVotou = false;

	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
	private Date dataVoto = new java.sql.Date(System.currentTimeMillis());

	// relacionamento N1N1 com pauta

	@ManyToMany(mappedBy = "associado", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnoreProperties({ "titulo", "texto", "inicioVotacao", "fimVotacao", "pautaAtiva", "totalVotos", "votosFavor",
			"votosContra", "aprovada", "associado" })
	private List<Pauta> pauta = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public boolean isJaVotou() {
		return jaVotou;
	}

	public void setJaVotou(boolean jaVotou) {
		this.jaVotou = jaVotou;
	}

	public boolean isVoto() {
		return voto;
	}

	public void setVoto(boolean voto) {
		this.voto = voto;
	}

	public Date getDataVoto() {
		return dataVoto;
	}

	public void setDataVoto(Date dataVoto) {
		this.dataVoto = dataVoto;
	}

	public List<Pauta> getPauta() {
		return pauta;
	}

	public void setPauta(List<Pauta> pauta) {
		this.pauta = pauta;
	}

}
