package pe.com.upao.grupo3.petsnature.serializers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PublicacionCreadaSerializer{
    private String categoria;
    private String correoU;
    private String tema;
    private String mascota;
    private String raza;
    private String titulo;
    private String descripcion;
    private String info;
    private String img;
}
