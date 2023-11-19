package pe.com.upao.grupo3.petsnature.serializers;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pe.com.upao.grupo3.petsnature.models.RazaAnimal;
import pe.com.upao.grupo3.petsnature.models.Tema;
import pe.com.upao.grupo3.petsnature.models.TipoMascota;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublicacionCreadaSerializer{
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "")
    private Date fecha;
    private Time hora;
    private String categoria;
    private Long id_usuario;
    private Tema tema;
    private TipoMascota tipoMascota;
    private String enlace;
    private String contenido;
    private String img;
    private int reacciones;
    private RazaAnimal razaAnimal;
}
