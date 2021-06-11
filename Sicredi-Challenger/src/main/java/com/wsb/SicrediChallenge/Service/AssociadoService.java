package com.wsb.SicrediChallenge.Service;

import java.util.*;
import java.time.*;
import com.wsb.SicrediChallenge.Model.*;
import com.wsb.SicrediChallenge.Repository.*;

import javassist.tools.rmi.ObjectNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {

	@Autowired
	private AssociadoRepository associadoRepository;

	@Autowired
	private PautaRepository pautaRepository;

	// se o associado já estiver cadastrado não fará o cadastro.
	public Optional<Associado> cadastrarAssociado(Associado associado) {
		if (associadoRepository.findByCpf(associado.getCpf()).isPresent() && associado.getId() == 0) {
			return null;
		}
		return Optional.of(associadoRepository.save(associado));
	}

	// se a pauta já existir não fara o cadastro da mesma.
	public Pauta cadastrarPauta(Pauta pauta) {
		
		if (pautaRepository.findByTituloContainingIgnoreCase(pauta.getTitulo()).isPresent()) {
			return null;
		}
		LocalTime tempoVotacao = LocalTime.now();
		pauta.setInicioVotacao(tempoVotacao);
		pauta.setFimVotacao(tempoVotacao);

		LocalTime cronometro = LocalTime.of(pauta.getFimVotacao().getHour(), pauta.getFimVotacao().getMinute(),
				pauta.getFimVotacao().getSecond()).plusMinutes(1);

		pauta.setFimVotacao(cronometro);
		pauta.setPautaAtiva(true);

		return pautaRepository.save(pauta);
	}

	public Pauta votarNaPauta(long idPauta, long idAssociado) {
		Optional<Pauta> pautaExiste = pautaRepository.findById(idPauta);
		Optional<Associado> associadoExiste = associadoRepository.findById(idAssociado);

		this.verificarEstadoPauta(pautaExiste.get());
		this.verificarCpf(associadoExiste.get(), pautaExiste.get());

		/*
		 * se a pauta/usuario existirem e o associado não tiver votado ainda entra na
		 * condição
		 */
		if (pautaExiste.isPresent() && associadoExiste.isPresent() && associadoExiste.get().isJaVotou() == false
				&& pautaExiste.get().isPautaAtiva() == true) {

			// adiciona o usuario na pauta existente;
			pautaExiste.get().getAssociado().add(associadoExiste.get());

			/* verifica o voto do associado */
			if (associadoExiste.get().isVoto() == true) {
				pautaExiste.get().setVotosFavor(pautaExiste.get().getVotosFavor() + 1);
				associadoExiste.get().setJaVotou(true);

			} else if (associadoExiste.get().isVoto() == false) {
				pautaExiste.get().setVotosContra(pautaExiste.get().getVotosContra() + 1);
				associadoExiste.get().setJaVotou(true);
			}

			/* verifica se a pauta foi ou nao aprovada */
			if (pautaExiste.get().getVotosFavor() > pautaExiste.get().getVotosContra()) {
				pautaExiste.get().setAprovada(true);

			} else {
				pautaExiste.get().setAprovada(false);
			}

			;
			pautaExiste.get().setTotalVotos(pautaExiste.get().getTotalVotos() + 1);
			return pautaRepository.save(pautaExiste.get());
		}
		// pautaExiste.orElseThrow()-> new ObjectNotFoundException("Voto não computado!");
		return null;
	}

	
	/* verifica se o tempo da votacao foi ultrapassado(1 minuto apos a criacao dapauta) */
	public void verificarEstadoPauta(Pauta pauta) {
		LocalTime tempoAtual = LocalTime.now();
		if (pauta.getFimVotacao().isBefore(tempoAtual)) {
			pauta.setPautaAtiva(false);
			pautaRepository.save(pauta);
		}
	}

	/* Apos a finalizacao da votacao da pauta em questão o usuario pode voltar a  votar	 */
	public void verificarCpf(Associado associado, Pauta pauta) {
		if (pauta.isPautaAtiva() == false) {
			associado.setJaVotou(false);
			associadoRepository.save(associado);
		}
	}
}
