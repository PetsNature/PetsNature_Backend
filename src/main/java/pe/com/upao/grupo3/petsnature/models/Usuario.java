package pe.com.upao.grupo3.petsnature.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import pe.com.upao.grupo3.petsnature.prejections.UsuarioProjection;


@Data
@AllArgsConstructor
@Entity
public class Usuario implements UsuarioProjection{

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
