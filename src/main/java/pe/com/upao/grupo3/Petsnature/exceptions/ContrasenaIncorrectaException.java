package pe.com.upao.grupo3.Petsnature.Exceptions;

public class ContrasenaIncorrectaException extends RuntimeException{
    public ContrasenaIncorrectaException(String mensaje){
        super(mensaje);
    }
}
