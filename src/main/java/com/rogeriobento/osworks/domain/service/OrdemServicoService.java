package com.rogeriobento.osworks.domain.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.rogeriobento.osworks.api.dto.OrdemServicoDto;
import com.rogeriobento.osworks.domain.model.OrdemServico;

public interface OrdemServicoService {

	public OrdemServicoDto criarOrdem(@Valid OrdemServico ordem, Long id);
	public List<OrdemServico> listarOrdens();
	public ResponseEntity<OrdemServico> buscarOrdem(Long id);
	
}
