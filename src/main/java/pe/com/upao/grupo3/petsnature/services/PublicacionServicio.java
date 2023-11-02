package pe.com.upao.grupo3.petsnature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upao.grupo3.petsnature.models.Publicacion;
import pe.com.upao.grupo3.petsnature.models.Usuario;
import pe.com.upao.grupo3.petsnature.prejections.UsuarioProjection;
import pe.com.upao.grupo3.petsnature.repositories.PublicacionRepository;
import pe.com.upao.grupo3.petsnature.serializers.PublicacionDTO;
import pe.com.upao.grupo3.petsnature.serializers.PublicacionUSerializer;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublicacionServicio {
    @Autowired
    private PublicacionRepository publicacionRepository;

    public Publicacion crearPublicacion(Publicacion publicacion){
        return publicacionRepository.save(publicacion);
    }

    public List<PublicacionDTO> filtrarporCategoria(String categoria){
        List<Object[]> results = publicacionRepository.findAllByCategoria(categoria);

        List<PublicacionDTO> publicacionesDTO = new ArrayList<>();
        for (Object[] result : results) {
            Publicacion publicacion = (Publicacion) result[0];
            UsuarioProjection usuarioProjection = (Usuario) result[1];

            PublicacionDTO publicacionDTO = new PublicacionDTO(publicacion, usuarioProjection);
            publicacionesDTO.add(publicacionDTO);
        }

        return publicacionesDTO;
    }
}
