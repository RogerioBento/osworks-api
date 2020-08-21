package com.rogeriobento.osworks.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rogeriobento.osworks.domain.model.Cliente;


public interface ClienteService {

    public List<Cliente> listar();
    
    public Cliente buscarCliente(Long cliente_id);
    
    public Cliente salvar(Cliente cliente);
    
    public ResponseEntity<Cliente> deletar(Long cliente_id);
    
    public ResponseEntity<Cliente> atualizar(Long cliente_id, Cliente cliente);
}
