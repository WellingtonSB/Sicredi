package com.wsb.SicrediChallenge.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="tb_pauta")
public class Pauta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String tema;
	
	private String texto;
	
	private Boolean pautaAtiva;//irá verificar se a pauta esta ativa, caso a mesma esteja inativa o voto não será contabilizado

	
	@OneToMany(mappedBy="pauta",cascade= CascadeType.ALL)
	@JsonIgnoreProperties("pauta")
	private List<Associado> associado;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getTema() {
		return tema;
	}


	public void setTema(String tema) {
		this.tema = tema;
	}


	public String getTexto() {
		return texto;
	}


	public void setTexto(String texto) {
		this.texto = texto;
	}


	public Boolean getPautaAtiva() {
		return pautaAtiva;
	}


	public void setPautaAtiva(Boolean pautaAtiva) {
		this.pautaAtiva = pautaAtiva;
	}


	public List<Associado> getAssociado() {
		return associado;
	}


	public void setAssociado(List<Associado> associado) {
		this.associado = associado;
	}	
	
}
