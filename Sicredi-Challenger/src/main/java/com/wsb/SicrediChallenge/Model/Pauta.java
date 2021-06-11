package com.wsb.SicrediChallenge.Model;

import java.time.*;
import java.util.*;
import javax.persistence.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.*;

@Entity
@Table(name = "tb_pauta")
public class Pauta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Size(max = 30)
	@Column(name = "titulo", nullable = false, unique = true)
	@NotEmpty(message = "Titulo deve ser preenchido")
	private String titulo;

	@Size(max = 240)
	@Column(name = "texto", nullable = false, unique = true)
	@NotEmpty(message = "O texto deve ser preenchido")
	private String texto;

	private LocalTime inicioVotacao;
	private LocalTime fimVotacao;
	private int totalVotos;
	private int votosFavor;
	private int votosContra;
	private boolean pautaAtiva;
	private boolean aprovada = false;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "pauta_associado", joinColumns = @JoinColumn(name = "pauta_id"), inverseJoinColumns = @JoinColumn(name = "associado_id"))
	@JsonIgnoreProperties({ "nome", "cpf", "jaVotou", "voto", "dataVoto", "pauta" })
	private List<Associado> associado = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalTime getInicioVotacao() {
		return inicioVotacao;
	}

	public void setInicioVotacao(LocalTime inicioVotacao) {
		this.inicioVotacao = inicioVotacao;
	}

	public LocalTime getFimVotacao() {
		return fimVotacao;
	}

	public void setFimVotacao(LocalTime fimVotacao) {
		this.fimVotacao = fimVotacao;
	}

	public boolean isPautaAtiva() {
		return pautaAtiva;
	}

	public void setPautaAtiva(boolean pautaAtiva) {
		this.pautaAtiva = pautaAtiva;
	}

	public int getTotalVotos() {
		return totalVotos;
	}

	public void setTotalVotos(int totalVotos) {
		this.totalVotos = totalVotos;
	}

	public int getVotosFavor() {
		return votosFavor;
	}

	public void setVotosFavor(int votosFavor) {
		this.votosFavor = votosFavor;
	}

	public int getVotosContra() {
		return votosContra;
	}

	public void setVotosContra(int votosContra) {
		this.votosContra = votosContra;
	}

	public boolean isAprovada() {
		return aprovada;
	}

	public void setAprovada(boolean aprovada) {
		this.aprovada = aprovada;
	}

	public List<Associado> getAssociado() {
		return associado;
	}

	public void setAssociado(List<Associado> associado) {
		this.associado = associado;
	}

}
