package com.wsb.SicrediChallenge.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wsb.SicrediChallenge.Model.Associado;
import com.wsb.SicrediChallenge.Model.Pauta;
import com.wsb.SicrediChallenge.Repository.AssociadoRepository;
import com.wsb.SicrediChallenge.Service.AssociadoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/associado")

@CrossOrigin(origins= "*", allowedHeaders = "*")

public class AssociadoController {

	@Autowired
	private AssociadoService service;

	@PostMapping("/votar")
	@ApiOperation(value = "Cadastra um associado")
	public ResponseEntity<Associado> post(@RequestBody Associado associado) {
		Optional<Associado> user = service.cadastrarAssociado(associado);
		try {
			return ResponseEntity.ok(user.get());
		}catch(Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
	
	@PutMapping("/pauta_associado/pauta/{idPauta}/associado/{idAssociado}")
	@ApiOperation(value = "Associa o voto do associado a pauta")
	public ResponseEntity<Pauta> putPauta(@PathVariable long idPauta, @PathVariable long idAssociado) {
		return ResponseEntity.ok(service.votarNaPauta(idPauta, idAssociado));
	}
	
}
