package pe.com.upao.grupo3.petsnature.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RazaAnimal {
    @Id
    private String nombre;
    @ManyToOne
    @JoinColumn(name = "nombre_mascota",nullable = false)
    private TipoMascota tipoMascota;

}
