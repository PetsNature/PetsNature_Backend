package pe.com.upao.grupo3.petsnature.serializers;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import pe.com.upao.grupo3.petsnature.models.Tema;
import pe.com.upao.grupo3.petsnature.models.TipoMascota;
import pe.com.upao.grupo3.petsnature.models.Usuario;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
public class PublicacionSerializer {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "")
    private Date fecha;
    private Time hora;
    private String contenido;
    private String img;
    private String enlace;
    private String nombreU;
    private String imgU;
}
