package pe.com.upao.grupo3.petsnature.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("Informacion")
public class Informacion extends Publicacion{
    private String titulo;
    private String descripcion;

    public Informacion(Usuario usuario, Tema tema, TipoMascota tipoMascota, String img, String contenido, String enlace,String titulo, String descripcion) {
        super(usuario, tema, tipoMascota,img, contenido,enlace);
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

//Esto serviria para poner una publicacion de tipo informacion :D

}
