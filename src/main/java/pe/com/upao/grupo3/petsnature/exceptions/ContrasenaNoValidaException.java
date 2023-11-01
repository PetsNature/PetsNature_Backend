package pe.com.upao.grupo3.petsnature.exceptions;

public class ContrasenaNoValidaException extends RuntimeException{
    public ContrasenaNoValidaException(String mensaje){
        super(mensaje);
    }
}
