package com.wsb.SicrediChallenge.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wsb.SicrediChallenge.Model.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {
	public Optional<Associado>findByCpf(String cpf);
	public boolean findByJaVotou(boolean jaVotou);
}
