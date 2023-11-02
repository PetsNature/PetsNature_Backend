package pe.com.upao.grupo3.petsnature.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
@Entity
public class Usuario{

    @Id
    private String correo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    @JsonIgnore
    private String contrasena;

    private String imgPerfil;

    public Usuario(){
    }

}
