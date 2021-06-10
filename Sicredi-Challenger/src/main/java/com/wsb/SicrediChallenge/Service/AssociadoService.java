package com.wsb.SicrediChallenge.Service;

import java.util.*;
import java.time.*;
import com.wsb.SicrediChallenge.Model.*;
import com.wsb.SicrediChallenge.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {

	@Autowired
	private AssociadoRepository associadoRepository;

	@Autowired
	private PautaRepository pautaRepository;

	public Associado cadastrarAssociado(Associado associado) {
		// se o associado já estiver cadastrado não fará o cadastro.
		if (associadoRepository.findByCpf(associado.getCpf()).isPresent() && associado.getId() == 0) {
			return null;
		}
		return associadoRepository.save(associado);
	}

	public Pauta cadastrarPauta(Pauta pauta) {
		// se a pauta já existir não fara o cadastro da mesma.
		if (pautaRepository.findByTituloContainingIgnoreCase(pauta.getTitulo()).isPresent()) {
			return null;
		}
		LocalTime tempoVotacao = LocalTime.now();
		pauta.setInicioVotacao(tempoVotacao);
		LocalTime a = LocalTime.now();
		pauta.setDuracaoVoto(a);

		LocalTime cronometro = LocalTime.of(pauta.getDuracaoVoto().getHour(), pauta.getDuracaoVoto().getMinute(),
				pauta.getDuracaoVoto().getSecond()).plusMinutes(1);
		pauta.setDuracaoVoto(cronometro);

		pauta.setPautaAtiva(true);
		return pautaRepository.save(pauta);
	}

	public Pauta votarNaPauta(long idPauta, long idAssociado) {
		Optional<Pauta> pautaExiste = pautaRepository.findById(idPauta);
		Optional<Associado> associadoExiste = associadoRepository.findById(idAssociado);
		this.verificarEstadoPauta(pautaExiste.get());

		// se a pauta/usuario existirem e o associado não tiver votado ainda entra na
		// condição

		if (pautaExiste.isPresent() && associadoExiste.isPresent() && associadoExiste.get().isJaVotou() == false
				&& pautaExiste.get().isPautaAtiva() == true) {

			// adiciona o usuario na pauta existente;
			pautaExiste.get().getAssociado().add(associadoExiste.get());
			if (associadoExiste.get().isVoto() == true) {
				pautaExiste.get().setVotosFavor(pautaExiste.get().getVotosFavor() + 1);
				associadoExiste.get().setJaVotou(true);

			} else if (associadoExiste.get().isVoto() == false) {
				pautaExiste.get().setVotosContra(pautaExiste.get().getVotosContra() + 1);
				associadoExiste.get().setJaVotou(true);
			}

			// se o numero de votos a favor for maior que o numero de votos contrarios a
			// pauta será aprovada
			if (pautaExiste.get().getVotosFavor() > pautaExiste.get().getVotosContra()) {
				pautaExiste.get().setEstado(true);

			} else {
				pautaExiste.get().setEstado(false);
			}

			pautaExiste.get().setTotalVotos(pautaExiste.get().getTotalVotos() + 1);

			return pautaRepository.save(pautaExiste.get());
		}

		return null;
	}

	public void verificarEstadoPauta(Pauta pauta) {
		LocalTime tempoAtual = LocalTime.now();
		if (pauta.getDuracaoVoto().isBefore(tempoAtual)) {
			System.out.println(pauta.isPautaAtiva());
			pauta.setPautaAtiva(false);
			pautaRepository.save(pauta);
		}
	}

}
