package pe.com.upao.grupo3.petsnature.serializers;

import lombok.AllArgsConstructor;
import lombok.Data;
import pe.com.upao.grupo3.petsnature.models.Tema;
import pe.com.upao.grupo3.petsnature.models.TipoMascota;
import pe.com.upao.grupo3.petsnature.models.Usuario;

@Data
@AllArgsConstructor
public class PublicacionSerializer {

    private Usuario usuario;
    private Tema tema;
    private TipoMascota tipoMascota;
    private String img;
    private String contenido;
    private String enlace;
    private String titulo;
    private String descripcion;
}
