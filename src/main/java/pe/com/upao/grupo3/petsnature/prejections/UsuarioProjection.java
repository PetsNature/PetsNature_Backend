package pe.com.upao.grupo3.petsnature.prejections;

import org.springframework.data.web.ProjectedPayload;

@ProjectedPayload
public interface UsuarioProjection {
    String getCorreo();
    String getNombre();
    String getImgPerfil();
}
