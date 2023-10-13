package pe.com.upao.grupo3.Petsnature.Serializers;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import pe.com.upao.grupo3.Petsnature.models.Usuario;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
public class PublicacionSerializer {

    private Usuario usuario;
    private String categoria;
    private String tema;
    private String mascota;
    private String raza;
    private String titulo;
    private String descripcion;
    private String info;
    private String img;
}
