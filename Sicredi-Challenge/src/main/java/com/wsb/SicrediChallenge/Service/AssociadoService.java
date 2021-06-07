package com.wsb.SicrediChallenge.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wsb.SicrediChallenge.Model.Associado;
import com.wsb.SicrediChallenge.Repository.AssociadoRepository;

@Service
public class AssociadoService {
	
	@Autowired
	private  AssociadoRepository repository;

	public Optional<Associado>RegistrarVoto(Associado associado){
		//caso o CPF cadastrado já tenha votado retornará um valor nulo.
		if(associado.getVoto()== true){
			return null;
		}
		
		return Optional.of(repository.save(associado));
	}
	
}
