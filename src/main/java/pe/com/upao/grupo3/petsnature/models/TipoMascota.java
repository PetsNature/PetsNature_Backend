package pe.com.upao.grupo3.petsnature.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TipoMascota {
    @Id
    private String nombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoMascota")
    private ArrayList<RazaAnimal> razas;
}
