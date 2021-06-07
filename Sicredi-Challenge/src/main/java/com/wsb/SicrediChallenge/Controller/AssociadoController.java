package com.wsb.SicrediChallenge.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wsb.SicrediChallenge.Model.Associado;
import com.wsb.SicrediChallenge.Service.AssociadoService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/associado")
public class AssociadoController {
	
	@Autowired
	private AssociadoService service;
	
	@PostMapping("/votar")
	public ResponseEntity<Associado>Post(@RequestBody Associado associado){
		Optional<Associado> user= service.RegistrarVoto(associado);
		try{
			return ResponseEntity.ok(user.get());
		}catch(Exception error) {
			return ResponseEntity.badRequest().build();
		}
	}
	
}
