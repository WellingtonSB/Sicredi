package com.wsb.SicrediChallenge.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wsb.SicrediChallenge.Model.Pauta;


@Repository
public interface PautaRepository  extends JpaRepository<Pauta,Long>{
	public List<Pauta>findByTemaContainingIgnoreCase(String tema);
	public List<Pauta>findByTextoContainingIgnoreCase(String texto);
}
