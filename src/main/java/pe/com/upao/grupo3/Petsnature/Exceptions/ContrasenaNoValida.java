package pe.com.upao.grupo3.Petsnature.Exceptions;

public class ContrasenaNoValida extends RuntimeException{
    public ContrasenaNoValida(String mensaje){
        super(mensaje);
    }
}
