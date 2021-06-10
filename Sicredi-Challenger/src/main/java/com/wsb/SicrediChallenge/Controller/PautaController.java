package com.wsb.SicrediChallenge.Controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wsb.SicrediChallenge.Model.Associado;
import com.wsb.SicrediChallenge.Model.Pauta;
import com.wsb.SicrediChallenge.Repository.PautaRepository;
import com.wsb.SicrediChallenge.Service.AssociadoService;

@RestController
@RequestMapping("/pauta")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PautaController {


	@Autowired
	private PautaRepository repository;
	
	@Autowired AssociadoService service;

	@GetMapping
	public ResponseEntity<List<Pauta>> GetAll(Pauta pauta) {
		return ResponseEntity.ok(repository.findAll());
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Pauta> post(@RequestBody Pauta pauta) {
	
		return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrarPauta(pauta));
	}
	
	@PutMapping("/pauta_associado/pauta/{idPauta}/associado/{idAssociado}")
	public ResponseEntity<Pauta> putPauta(@PathVariable long idPauta,@PathVariable long idAssociado){
		return ResponseEntity.ok(service.votarNaPauta(idPauta,idAssociado));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id){
		repository.deleteById(id);
	}
	
}

