package pe.com.upao.grupo3.petsnature.exceptions;

public class PublicacionNoExisteException extends RuntimeException{
    public PublicacionNoExisteException(String mensaje){
        super(mensaje);
    }
}
