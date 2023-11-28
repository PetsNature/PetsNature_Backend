package pe.com.upao.grupo3.petsnature.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@DiscriminatorValue("Recomendacion")
public class Recomendacion extends Publicacion{

    public Recomendacion(Usuario usuario, Tema tema, TipoMascota tipoMascota, String img, String contenido, String enlace) {
        super(usuario, tema, tipoMascota, img, contenido, enlace);
    }
}
//Esto serviria para poner una publicacion de tipo recomendacion :D