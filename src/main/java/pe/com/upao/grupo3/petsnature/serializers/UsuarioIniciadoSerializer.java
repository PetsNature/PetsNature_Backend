package pe.com.upao.grupo3.petsnature.serializers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioIniciadoSerializer {
    Long id;
    String correo;
    String nombre;
    String imgPerfil;
    String token;
}
