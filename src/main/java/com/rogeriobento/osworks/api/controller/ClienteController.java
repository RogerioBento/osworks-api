package com.rogeriobento.osworks.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rogeriobento.osworks.domain.model.Cliente;
import com.rogeriobento.osworks.domain.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping()
	public List<Cliente> listar() {
		return clienteService.listar();
	}

	@GetMapping(value = "/{cliente_id}")
	public Cliente buscarCliente(@Valid @PathVariable Long cliente_id) {
		return clienteService.buscarCliente(cliente_id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente salvar(@Valid @RequestBody Cliente cliente) {
		return clienteService.salvar(cliente);
	}

	@DeleteMapping("/{cliente_id}")
	public ResponseEntity<Cliente> deletar(@Valid @PathVariable Long cliente_id) {
		return clienteService.deletar(cliente_id);
	}

	@PutMapping("/{cliente_id}")
	public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long cliente_id, @RequestBody Cliente cliente) {
		return clienteService.atualizar(cliente_id, cliente);
	}

}
