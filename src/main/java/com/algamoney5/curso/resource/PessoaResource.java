package com.algamoney5.curso.resource;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algamoney5.curso.model.Pessoa;
import com.algamoney5.curso.repository.PessoaRespository;
import com.algamoney5.curso.service.PessoaService;

@RestController
@RequestMapping("pessoas")
public class PessoaResource {

	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaRespository pessoaRepository;
	
//	@GetMapping
//	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PESSOA') and #oauth2.hasScope('read')")
//	public List<Pessoa> listar(){
//		return pessoaService.litarService();
//	}
	
	@GetMapping
	public Page<Pessoa> perquisar(@RequestParam(required = false, defaultValue = "%") String nome, Pageable pageable){		
		return  pessoaRepository.findByNomeContaining(nome, pageable);
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa){		Pessoa pessoaSalva = pessoaService.criarService(pessoa);
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PESSOA') and #oauth2.hasScope('write')")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa){		
		Pessoa pessoaSalva = pessoaService.atualizarService(codigo, pessoa);		
		return ResponseEntity.ok(pessoaSalva);
	}
	
	@DeleteMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PESSOA') and #oauth2.hasScope('write')")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo){
		pessoaService.remover(codigo);
	}
}
