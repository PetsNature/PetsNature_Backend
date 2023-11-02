package pe.com.upao.grupo3.petsnature.exceptions;

public class CategoriaNoExistenteException extends RuntimeException{
    public CategoriaNoExistenteException(String mensaje){
        super(mensaje);
    }
}
