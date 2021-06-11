package com.wsb.SicrediChallenge.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wsb.SicrediChallenge.Model.Pauta;

public interface PautaRepository extends JpaRepository<Pauta, Long>{
	public Optional<Pauta>findByTituloContainingIgnoreCase(String titulo);
}
