package pe.com.upao.grupo3.petsnature.serializers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.upao.grupo3.petsnature.models.Tema;
import pe.com.upao.grupo3.petsnature.models.TipoMascota;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionCreadaSerializer{
    private Date fecha;
    private Time hora;
    private String categoria;
    private String correoU;
    private Tema tema;
    private TipoMascota tipoMascota;
    private String enlace;
    private String contenido;
    private String img;
}
