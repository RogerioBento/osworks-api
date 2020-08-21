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

import net.bytebuddy.asm.Advice.Return;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.rogeriobento.osworks.api.dto.OrdemServicoDto;
import com.rogeriobento.osworks.api.exceptionhandler.ClienteNotFoundException;
import com.rogeriobento.osworks.api.exceptionhandler.NegocioException;
import com.rogeriobento.osworks.api.exceptionhandler.OrdemNotFoundException;
import com.rogeriobento.osworks.domain.model.Cliente;
import com.rogeriobento.osworks.domain.model.Comentario;
import com.rogeriobento.osworks.domain.model.OrdemServico;
import com.rogeriobento.osworks.domain.model.StatusOrdemServico;
import com.rogeriobento.osworks.domain.repository.ClienteRepository;
import com.rogeriobento.osworks.domain.repository.ComentarioRepository;
import com.rogeriobento.osworks.domain.repository.OrdemServicoRepository;
import com.rogeriobento.osworks.domain.service.OrdemServicoService;

@Service
public class OrdemServicoServiceIImpl implements OrdemServicoService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private OrdemServicoRepository ordemRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ComentarioRepository comentarioRepository;

	@Override
	public OrdemServicoDto criarOrdem(OrdemServico ordem) {
		Cliente cliente = clienteRepository.findById(ordem.getCliente().getId())
				.orElseThrow(() -> new ClienteNotFoundException());

		ordem.setCliente(cliente);
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
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = buscar(ordemServicoId);

		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);

		return comentarioRepository.save(comentario);
	}

	public void finalizar(Long ordemServicoId) throws NegocioException {
		OrdemServico ordemServico = buscar(ordemServicoId);
		ordemServico.finalizar();
		ordemRepository.save(ordemServico);
	}

	//Método BUSCAR Ordem de serviço
	private OrdemServico buscar(Long ordemServicoId) {
		return ordemRepository.findById(ordemServicoId)
				.orElseThrow(() -> new OrdemNotFoundException());
	}

	
	//Ordem to Model (DTO)
	private OrdemServicoDto ordemToModel(OrdemServico ordem) {
		return modelMapper.map(ordem, OrdemServicoDto.class);
	}
	
	//Ordem List ->  Dto List
	private List<OrdemServicoDto> toCollectionModel(List<OrdemServico> ordemServicoList){
		return ordemServicoList.stream()
				.map(ordemServico -> ordemToModel(ordemServico))
				.collect(Collectors.toList());
	}
	
}
