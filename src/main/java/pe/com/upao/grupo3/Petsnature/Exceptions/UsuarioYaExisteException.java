package pe.com.upao.grupo3.Petsnature.Exceptions;

public class UsuarioYaExisteException extends RuntimeException{
    public UsuarioYaExisteException(String mensaje){
        super(mensaje);
    }
}
