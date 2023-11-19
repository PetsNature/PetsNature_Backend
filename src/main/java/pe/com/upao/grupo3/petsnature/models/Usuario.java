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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    @JsonIgnore
    private String contrasena;

    private String imgPerfil;

    public Usuario(){
    }

    public Usuario(String correo, String nombre, String contrasena) {
        this.correo = correo;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public boolean validarContrasena() {
        int simbolos = 0;
        int numeros = 0;

        for (int i = 0; i < contrasena.length(); i++) {
            char caracter = contrasena.charAt(i);

            if ((caracter >= 33 && caracter <= 47) || (caracter >= 58 && caracter <= 64) || (caracter >= 91 && caracter <= 96)) {
                simbolos++;
            }

            if (caracter >= 48 && caracter <= 57) {
                numeros++;
            }
        }

        return simbolos > 0 && numeros > 0 && contrasena.length() > 5;
    }
}
