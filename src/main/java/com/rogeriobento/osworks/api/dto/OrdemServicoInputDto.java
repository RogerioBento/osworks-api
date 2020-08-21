package com.rogeriobento.osworks.api.dto;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrdemServicoInputDto {
    
    @NotBlank
    private String descricao;

    @NotNull
    private BigDecimal preco; 

    @Valid
    @NotNull
    private ClienteInput cliente; 

    public ClienteInput getCliente() {
		return cliente;
	}
	public void setCliente(ClienteInput cliente) {
		this.cliente = cliente;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

}