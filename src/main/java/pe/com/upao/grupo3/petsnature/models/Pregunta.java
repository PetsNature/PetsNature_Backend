package pe.com.upao.grupo3.petsnature.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@DiscriminatorValue("Pregunta")
public class Pregunta extends Publicacion{

    public Pregunta(Usuario usuario, Tema tema, TipoMascota tipoMascota, String img, String contenido, String enlace) {
        super(usuario, tema, tipoMascota, img, contenido, enlace);
    }
}
