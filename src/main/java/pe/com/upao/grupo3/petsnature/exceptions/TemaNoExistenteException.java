package pe.com.upao.grupo3.petsnature.exceptions;

public class TemaNoExistenteException extends RuntimeException {
    public TemaNoExistenteException(String mensaje) {
        super(mensaje);
    }
}
