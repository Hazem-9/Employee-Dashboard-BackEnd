package net.java.springbackend.exeption;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResoureNotFoundExeption extends RuntimeException{

    public static final long serialVersionUID = 1L;


    public ResoureNotFoundExeption (String message){
        super(message);

    }
}