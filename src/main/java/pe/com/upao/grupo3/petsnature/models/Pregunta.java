package pe.com.upao.grupo3.petsnature.models;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("Pregunta")
public class Pregunta extends Publicacion{
    @Column(nullable = false)
    private String info;

    public Pregunta(Usuario usuario, String tema, String mascota, String raza, String img, String info) {
        super(usuario, tema, mascota, raza, img);
        this.info = info;
    }
}
