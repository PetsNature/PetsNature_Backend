package pe.com.upao.grupo3.petsnature.serializers;

import lombok.AllArgsConstructor;
import lombok.Data;
import pe.com.upao.grupo3.petsnature.models.Usuario;

@Data
@AllArgsConstructor
public class PublicacionSerializer {

    private Usuario usuario;
    private String tema;
    private String mascota;
    private String raza;
    private String titulo;
    private String descripcion;
    private String info;
    private String img;
}
