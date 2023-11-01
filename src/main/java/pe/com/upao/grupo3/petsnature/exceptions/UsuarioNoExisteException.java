package pe.com.upao.grupo3.petsnature.exceptions;


public class UsuarioNoExisteException extends RuntimeException{
    public UsuarioNoExisteException(String mensaje){
        super(mensaje);
    }
}
