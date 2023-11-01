package pe.com.upao.grupo3.Petsnature.Exceptions;


public class UsuarioNoExisteException extends RuntimeException{
    public UsuarioNoExisteException(String mensaje){
        super(mensaje);
    }
}
