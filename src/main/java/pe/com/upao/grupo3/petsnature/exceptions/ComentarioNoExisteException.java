package pe.com.upao.grupo3.petsnature.exceptions;

public class ComentarioNoExisteException extends RuntimeException {
    public ComentarioNoExisteException(String mensaje) {
        super(mensaje);
    }
}
