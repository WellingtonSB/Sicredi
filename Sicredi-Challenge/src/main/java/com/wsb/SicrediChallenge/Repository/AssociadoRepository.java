package com.wsb.SicrediChallenge.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wsb.SicrediChallenge.Model.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long>{
	public List<Associado>findByCpf(String cpf);
}
