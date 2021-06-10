package com.wsb.SicrediChallenge.Repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wsb.SicrediChallenge.Model.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Long> {
	public Optional<Pauta>findByTituloContainingIgnoreCase(String titulo);
}
