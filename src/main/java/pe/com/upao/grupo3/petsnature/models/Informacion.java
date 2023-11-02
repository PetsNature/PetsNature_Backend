package pe.com.upao.grupo3.petsnature.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("Informacion")
public class Informacion extends Publicacion{
    private String titulo;
    private String descripcion;
    @Column(nullable = false)
    private String info;

    public Informacion(Usuario usuario, String tema, String mascota, String raza, String img, String titulo, String descripcion, String info) {
        super(usuario, tema, mascota, raza, img);
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.info = info;
    }

}
