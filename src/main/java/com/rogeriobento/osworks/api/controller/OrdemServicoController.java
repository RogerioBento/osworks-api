package com.rogeriobento.osworks.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rogeriobento.osworks.api.dto.OrdemServicoDto;
import com.rogeriobento.osworks.api.dto.OrdemServicoInputDto;
import com.rogeriobento.osworks.api.exceptionhandler.NegocioException;
import com.rogeriobento.osworks.domain.model.OrdemServico;
import com.rogeriobento.osworks.domain.service.OrdemServicoService;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private OrdemServicoService ordemServico;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrdemServicoDto criarOrdem(@Valid @RequestBody OrdemServicoInputDto ordemInputDto) {
		OrdemServico ordemEntity = ToEntity(ordemInputDto);
		return ordemServico.criarOrdem(ordemEntity);
	}

	@GetMapping
	@ResponseStatus(HttpStatus.FOUND)
	public List<OrdemServico> listarOrdens() {
		return ordemServico.listarOrdens();
	}

	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.FOUND)
	public ResponseEntity<OrdemServico> buscarOrdem(@Valid @PathVariable Long id) {
		return ordemServico.buscarOrdem(id);
	}

	@PutMapping("/{ordemServicoId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long ordemServicoId) throws NegocioException {
		ordemServico.finalizar(ordemServicoId);
	}
	
	private OrdemServico ToEntity(OrdemServicoInputDto ordemInputDto){
		return modelMapper.map(ordemInputDto, OrdemServico.class);
	}
}
