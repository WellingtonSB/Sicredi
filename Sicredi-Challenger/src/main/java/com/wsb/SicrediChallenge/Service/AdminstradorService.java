package com.wsb.SicrediChallenge.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wsb.SicrediChallenge.Model.AdminstradorModel;
import com.wsb.SicrediChallenge.Model.AdminstradorLogin;
import com.wsb.SicrediChallenge.Repository.AdminstradorRepository;

import java.nio.charset.Charset;

import java.util.Optional;
import org.apache.commons.codec.binary.Base64;

@Service
public class AdminstradorService {

	@Autowired
	private AdminstradorRepository repository;

	public Optional<AdminstradorModel> CadastrarUsuario(AdminstradorModel adm) {

		if (repository.findByUsuario(adm.getUsuario()).isPresent()) {
			return null;
		}

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		String senhaEncoder = encoder.encode(adm.getSenha());
		adm.setSenha(senhaEncoder);

		return Optional.of(repository.save(adm));
	}

	public Optional<AdminstradorLogin> Logar(Optional<AdminstradorLogin> adm) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<AdminstradorModel> administrador = repository.findByUsuario(adm.get().getUsuario());

		if (administrador.isPresent()) {
			if (encoder.matches(adm.get().getSenha(), administrador.get().getSenha())) {

				String auth = adm.get().getUsuario() + ":" + adm.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);

				adm.get().setToken(authHeader);
				adm.get().setNome(administrador.get().getNome());
				adm.get().setSenha(administrador.get().getSenha());

				return adm;

			}
		}
		return null;
	}
}