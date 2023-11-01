package pe.com.upao.grupo3.petsnature.exceptions;

public class ContrasenaIncorrectaException extends RuntimeException{
    public ContrasenaIncorrectaException(String mensaje){
        super(mensaje);
    }
}
