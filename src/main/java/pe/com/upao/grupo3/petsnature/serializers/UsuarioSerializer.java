package pe.com.upao.grupo3.petsnature.serializers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioSerializer {
    String correo;
    String nombre;
    String contrasena;
    String imgPerfil;
}
