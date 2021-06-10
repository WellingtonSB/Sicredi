package com.wsb.SicrediChallenge.TesteAPI;

import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.wsb.SicrediChallenge.Model.Associado;
import com.wsb.SicrediChallenge.Model.Pauta;
import com.wsb.SicrediChallenge.Repository.AssociadoRepository;
import com.wsb.SicrediChallenge.Repository.PautaRepository;
import com.wsb.SicrediChallenge.Service.AssociadoService;

@RunWith(SpringRunner.class)
//@DataJpaTest
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AssociadoRepositoryIntegrationTest {

	@Autowired
	private PautaRepository pautaRepository;
	private Pauta pauta;

	@Autowired
	private AssociadoRepository associadoRepository;
	private Associado associado;

	@Autowired
	private AssociadoService service;
	
	@Autowired
	private TestRestTemplate testRestTemplate;	
	
	
	@Before
	public void start() {
		LocalTime inicio =LocalTime.now();
		LocalTime fim = LocalTime.now().plusMinutes(1);
		
		associado = new Associado("Wellington", "62157711805",true,false);
		
		associado = new Associado("Joao", "20184625041",false,true);
	
		pauta = new Pauta("Verbas 2021", "Lorem Ipsum is simply",inicio,fim,true,10,6,5,true);


	}

	/*====================<ASSOCIADO>====================*/
	@Test(expected = RuntimeException.class)
	public void salvarNomeNulo() throws Exception {
		associado.setNome(null);
		associadoRepository.save(associado);
	}

	@Test(expected = RuntimeException.class)
	public void salvarComNomeMaiorQueOPermitido() throws Exception {
		associado.setNome("nomemaiorsssssssssssssssssssssssssssssssssssssss");
		associadoRepository.save(associado);
	}

	@Test(expected = RuntimeException.class)
	public void salvarComCpfNulo() throws Exception {
		associado.setCpf(null);
		associadoRepository.save(associado);
	}

	@Test(expected = RuntimeException.class)
	public void salvarComCpfInvalido() throws Exception {
		associado.setCpf("6215771180");
		associadoRepository.save(associado);
	}
	
	@Test
	public void salvaComVotosFalse()throws Exception{
		if(associado.isJaVotou()==false) {
			associadoRepository.save(associado);
		}
	}
	
	@Test
	public void fazerOsCadastroDoAssociado() {
		ResponseEntity<String> resposta = testRestTemplate.exchange("/associado/cadastrar",HttpMethod.POST,null, String.class);
		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	
	/*====================<PAUTA>====================*/
	@Test(expected = RuntimeException.class)
	public void salvarComTituloNulo() throws Exception {
		pauta.setTitulo(null);
		pautaRepository.save(pauta);
	}

	@Test(expected = RuntimeException.class)
	public void salvarTituloMaiorQueOPermitido() throws Exception {
		pauta.setTitulo("Contrary to popular belief, Lorem Ipsum is not simply random text. ");
		pautaRepository.save(pauta);
	}

	@Test(expected = RuntimeException.class)
	public void salvarComTextoNulo() throws Exception {
		pauta.setTexto(null);
		pautaRepository.save(pauta);
	}

	@Test(expected = RuntimeException.class)
	public void salvarTextoMaiorQueOPermitido() throws Exception {
		pauta.setTexto(
				"It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.");
		pautaRepository.save(pauta);
	}
	
	@Test
	public void buscarTodasAsPautas() {
		ResponseEntity<String> resposta = testRestTemplate.exchange("/pauta",HttpMethod.GET,null, String.class);
		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}
	
	@Test
	public void cadastrarPauta() {
		ResponseEntity<String> resposta = testRestTemplate.exchange("/pauta/cadastrar",HttpMethod.POST,null, String.class);
		Assert.assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}


	

}
