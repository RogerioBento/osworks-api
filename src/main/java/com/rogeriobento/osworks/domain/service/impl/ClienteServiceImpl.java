package com.rogeriobento.osworks.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rogeriobento.osworks.domain.model.Cliente;
import com.rogeriobento.osworks.domain.repository.ClienteRepository;
import com.rogeriobento.osworks.domain.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	@Override
	public Cliente buscarCliente(Long cliente_id) {
		Optional<Cliente> cliente = clienteRepository.findById(cliente_id);
		return cliente.isEmpty() ? null : cliente.get() ;
		
	}

	@Override
	public Cliente salvar(Cliente cliente) {
		return clienteRepository.save(cliente);
	}

	@Override
	public ResponseEntity<Cliente> deletar(Long cliente_id) {
		Optional<Cliente> clienteId = clienteRepository.findById(cliente_id);
		if(!clienteId.isEmpty()) { 
			clienteRepository.deleteById(cliente_id);
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.noContent().build();
		}
	}

	@Override
	public ResponseEntity<Cliente> atualizar(Long cliente_id, Cliente cliente) {
		Optional<Cliente> clienteId = clienteRepository.findById(cliente_id);
		if(clienteId.isPresent()) {
			clienteId.ifPresent(temp -> {
				temp.setNome(cliente.getNome());
				temp.setEmail(cliente.getEmail());
				temp.setTelefone(cliente.getTelefone());
				;
			});
			clienteRepository.save(clienteId.get());
			return ResponseEntity.ok().build();
		}else {
			return ResponseEntity.noContent().build();
		}
	}

}
