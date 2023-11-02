package pe.com.upao.grupo3.petsnature.serializers;

import lombok.AllArgsConstructor;
import lombok.Data;
import pe.com.upao.grupo3.petsnature.models.Publicacion;
import pe.com.upao.grupo3.petsnature.prejections.UsuarioProjection;

@Data
@AllArgsConstructor
public class PublicacionDTO {
    Publicacion publicacion;
    UsuarioProjection usuarioProjection;
}
