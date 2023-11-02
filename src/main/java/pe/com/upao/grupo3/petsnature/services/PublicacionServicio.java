package pe.com.upao.grupo3.petsnature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upao.grupo3.petsnature.models.Publicacion;
import pe.com.upao.grupo3.petsnature.repositories.PublicacionRepository;

import java.util.List;

@Service
public class PublicacionServicio {
    @Autowired
    private PublicacionRepository publicacionRepository;

    public Publicacion crearPublicacion(Publicacion publicacion){
        return publicacionRepository.save(publicacion);
    }

    public List<Publicacion> filtrarporCategoria(String categoria){

        return publicacionRepository.findAllByCategoria(categoria);
    }
}
