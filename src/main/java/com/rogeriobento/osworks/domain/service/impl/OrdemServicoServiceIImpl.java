package com.rogeriobento.osworks.domain.service.impl;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.aspectj.weaver.ast.Var;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.rogeriobento.osworks.api.dto.OrdemServicoDto;
import com.rogeriobento.osworks.api.exceptionhandler.ClienteNotFoundException;
import com.rogeriobento.osworks.domain.model.Cliente;
import com.rogeriobento.osworks.domain.model.OrdemServico;
import com.rogeriobento.osworks.domain.model.StatusOrdemServico;
import com.rogeriobento.osworks.domain.repository.ClienteRepository;
import com.rogeriobento.osworks.domain.repository.OrdemServicoRepository;
import com.rogeriobento.osworks.domain.service.OrdemServicoService;

@Service
public class OrdemServicoServiceIImpl implements OrdemServicoService{

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private OrdemServicoRepository ordemRepository;
	
	@Autowired
	private ClienteRepository clienteRepository; 
	
	
	@Override
	public OrdemServicoDto criarOrdem(OrdemServico ordem,  Long id) {
		 Optional<OrdemServico> ordemId = ordemRepository.findById(id);
		
		 //ordem.setId((Long) ordemId);
		 ordem.setStatus(StatusOrdemServico.ABERTA);
		 ordem.setDataAbertura(OffsetDateTime.now());	
		return ordemToModel(ordemRepository.save(ordem));
	}

	
	@Override
	public List<OrdemServico> listarOrdens() {
		return ordemRepository.findAll();
	}


	
	@Override
	public ResponseEntity<OrdemServico> buscarOrdem(Long id) {
		Optional<OrdemServico> ordemId = ordemRepository.findById(id);
		if (!ordemId.isEmpty()) {
			OrdemServicoDto ordemServicoDto = ordemToModel(ordemId.get());
			return ResponseEntity.ok(ordemId.get());
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	
	private OrdemServicoDto ordemToModel(OrdemServico ordem) {
		return modelMapper.map(ordem, OrdemServicoDto.class);
	}
	
	private List<OrdemServicoDto> toCollectionModel(List<OrdemServico> ordemServicoList){
		return ordemServicoList.stream()
				.map(ordemServico -> ordemToModel(ordemServico))
				.collect(Collectors.toList());
	}
	
}
