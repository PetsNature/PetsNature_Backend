package pe.com.upao.grupo3.petsnature.serializers;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ComentarioSerializer {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "")
    private Date fecha;
    private String contenido;
    private String autor;
    private String imgAutor;

}
