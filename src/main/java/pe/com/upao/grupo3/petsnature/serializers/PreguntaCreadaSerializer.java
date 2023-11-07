package pe.com.upao.grupo3.petsnature.serializers;

import lombok.AllArgsConstructor;
import lombok.Data;
import pe.com.upao.grupo3.petsnature.models.Tema;
import pe.com.upao.grupo3.petsnature.models.TipoMascota;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
public class PreguntaCreadaSerializer extends PublicacionCreadaSerializer{

    public PreguntaCreadaSerializer(Date fecha, Time hora, String categoria, String correoU, Tema tema, TipoMascota tipoMascota, String enlace, String contenido, String img) {
        super(fecha, hora, categoria, correoU, tema, tipoMascota, enlace, contenido, img);
    }
}
