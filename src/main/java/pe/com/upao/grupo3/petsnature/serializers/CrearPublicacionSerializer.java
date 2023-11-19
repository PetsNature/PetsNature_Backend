package pe.com.upao.grupo3.petsnature.serializers;

import lombok.AllArgsConstructor;
import lombok.Data;
import pe.com.upao.grupo3.petsnature.models.RazaAnimal;
import pe.com.upao.grupo3.petsnature.models.Tema;
import pe.com.upao.grupo3.petsnature.models.TipoMascota;

@Data
@AllArgsConstructor
public class CrearPublicacionSerializer {
    private Tema tema;
    private TipoMascota tipoMascota;
    private RazaAnimal razaAnimal;
    private String img;
    private String contenido;
    private String categoria;
    private String enlace;
    private String titulo;
    private String descripcion;
}
