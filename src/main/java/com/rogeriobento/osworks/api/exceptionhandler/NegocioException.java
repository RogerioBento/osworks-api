package com.rogeriobento.osworks.api.exceptionhandler;

public class NegocioException extends Exception{
    
    public  NegocioException(){
        super();
    }

    public  NegocioException(String message) {
        super(message);
    }
}