package com.wsb.SicrediChallenge.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wsb.SicrediChallenge.Model.AdminstradorModel;
import com.wsb.SicrediChallenge.Model.Associado;
import com.wsb.SicrediChallenge.Model.Pauta;
import com.wsb.SicrediChallenge.Repository.AssociadoRepository;
import com.wsb.SicrediChallenge.Repository.PautaRepository;
import com.wsb.SicrediChallenge.Model.AdminstradorLogin;
import com.wsb.SicrediChallenge.Service.AdminstradorService;
import com.wsb.SicrediChallenge.Service.AssociadoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/adminstrador")
@CrossOrigin(origins = "*", allowedHeaders = "*")

public class AdminstradorController {

	@Autowired
	private AdminstradorService serviceAdministrador;

	@Autowired
	private AssociadoService assocaciadoService;
	
	@Autowired
	private AssociadoRepository associadoRepository;

	@Autowired
	private PautaRepository pautaRepository;

	@GetMapping("/associados")
	@ApiOperation(value = "Buscar todos os associados")
	public ResponseEntity<List<Associado>> getAll() {
		return ResponseEntity.ok(associadoRepository.findAll());
	}

	@GetMapping("/pautas")
	@ApiOperation(value = "Buscar todas as pautas")
	public ResponseEntity<List<Pauta>> GetAll(Pauta pauta) {
		return ResponseEntity.ok(pautaRepository.findAll());
	}

	@PostMapping("/cadastrar/pauta")
	@ApiOperation(value = "Cadastra uma nova pauta")
	public ResponseEntity<Pauta> post(@RequestBody Pauta pauta) {
		return ResponseEntity.status(HttpStatus.CREATED).body(assocaciadoService.cadastrarPauta(pauta));
	}

	@PostMapping("/logar")
	@ApiOperation(value = "Login do adminstrador")
	public ResponseEntity<AdminstradorLogin> Autentication(@RequestBody Optional<AdminstradorLogin> adm) {
		return serviceAdministrador.Logar(adm).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}

	@PostMapping("/cadastrar/adm")
	@ApiOperation(value = "Cadastrar um novo adminstrador")
	public ResponseEntity<AdminstradorModel> post(@RequestBody AdminstradorModel adm) {
		Optional<AdminstradorModel> admir = serviceAdministrador.CadastrarUsuario(adm);
		try {
			return ResponseEntity.ok(admir.get());
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}


	
	
	@DeleteMapping("/deletar/pauta/{id}")
	public void deletarPauta(@PathVariable long id) {
		pautaRepository.deleteById(id);
	}

	@DeleteMapping("/deletar/associado/{id}")
	public void deletarAssociado(@PathVariable long id) {
		associadoRepository.deleteById(id);
	}

}