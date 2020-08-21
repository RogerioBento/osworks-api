package com.rogeriobento.osworks.api.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.rogeriobento.osworks.api.dto.ComentarioDto;
import com.rogeriobento.osworks.api.dto.ComentarioInput;
import com.rogeriobento.osworks.api.exceptionhandler.OrdemNotFoundException;
import com.rogeriobento.osworks.domain.model.Comentario;
import com.rogeriobento.osworks.domain.model.OrdemServico;
import com.rogeriobento.osworks.domain.repository.OrdemServicoRepository;
import com.rogeriobento.osworks.domain.service.OrdemServicoService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ordens-servico/{ordemServicoId}/comentarios")
public class ComentarioController {

    @Autowired
    private OrdemServicoService ordemServico;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @GetMapping
    public List<ComentarioDto> listar(@PathVariable Long ordemServicoId){
        OrdemServico ordemServico = ordemServicoRepository.findById(ordemServicoId)
                .orElseThrow(() -> new OrdemNotFoundException());
        return toCollectionDto(ordemServico.getComentarios());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ComentarioDto adicionarComentario(@PathVariable Long ordemServicoId, @Valid @RequestBody ComentarioInput comentarioInput){
        Comentario comentario = ordemServico.adicionarComentario(ordemServicoId, comentarioInput.getDescricao());
        return toModel(comentario);
    }

    private ComentarioDto toModel(Comentario comentario){
        return modelMapper.map(comentario, ComentarioDto.class);
    }

    private List<ComentarioDto> toCollectionDto(List<Comentario> comentarios){
        return comentarios.stream()
                .map(comentario -> toModel(comentario))
                .collect(Collectors.toList());
    }
}