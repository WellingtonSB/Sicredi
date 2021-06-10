package com.wsb.SicrediChallenge.Controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wsb.SicrediChallenge.Model.Associado;
import com.wsb.SicrediChallenge.Model.Pauta;
import com.wsb.SicrediChallenge.Repository.AssociadoRepository;
import com.wsb.SicrediChallenge.Service.AssociadoService;


@RestController
@RequestMapping("/associado")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AssociadoController {

	@Autowired
	private AssociadoRepository repository;
	
	@Autowired
	private AssociadoService service;

	@GetMapping
	public ResponseEntity<List<Associado>>getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Associado> post(@RequestBody Associado associado) {
	return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarAssociado(associado));
	}

	
	
/*	@PutMapping
	public ResponseEntity<Associado>put(@RequestBody Associado associado, Pauta pauta){
		return ResponseEntity.ok(service.votarNaPauta(pauta, associado));
	}*/

}
