package pe.com.upao.grupo3.petsnature.exceptions;

public class UsuarioYaExisteException extends RuntimeException{
    public UsuarioYaExisteException(String mensaje){
        super(mensaje);
    }
}
