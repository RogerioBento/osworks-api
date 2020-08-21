package com.rogeriobento.osworks.api.dto;

import javax.validation.constraints.NotNull;

public class ClienteInput {

    @NotNull
    private Long id;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }

}
