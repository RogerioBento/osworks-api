package com.rogeriobento.osworks.domain.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.rogeriobento.osworks.api.dto.OrdemServicoDto;
import com.rogeriobento.osworks.api.dto.OrdemServicoInputDto;
import com.rogeriobento.osworks.api.exceptionhandler.NegocioException;
import com.rogeriobento.osworks.domain.model.Comentario;
import com.rogeriobento.osworks.domain.model.OrdemServico;

public interface OrdemServicoService {

	public OrdemServicoDto criarOrdem(@Valid OrdemServico ordemEntity);
	public List<OrdemServico> listarOrdens();
	public ResponseEntity<OrdemServico> buscarOrdem(Long id);
	public Comentario adicionarComentario(Long ordemServicoid, String descricao);
	public void finalizar(Long ordemServicoId) throws NegocioException;
}
