package pe.com.upao.grupo3.petsnature.serializers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioPerfilSerializer {
    private String correo;
    private String nombre;
    private String contrasena;
    private String imgPerfil;
}
