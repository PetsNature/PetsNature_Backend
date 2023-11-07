package pe.com.upao.grupo3.petsnature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upao.grupo3.petsnature.exceptions.CategoriaNoExistenteException;
import pe.com.upao.grupo3.petsnature.exceptions.PublicacionNoExisteException;
import pe.com.upao.grupo3.petsnature.models.Publicacion;
import pe.com.upao.grupo3.petsnature.repositories.PublicacionRepository;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PublicacionServicio {
    @Autowired
    private PublicacionRepository publicacionRepository;

    public Publicacion crearPublicacion(Publicacion publicacion){
        return publicacionRepository.save(publicacion);
    }

    public List<Publicacion> filtrarporCategoria(String categoria){
        if (publicacionRepository.findAllByCategoria(categoria).isEmpty()){
            throw new CategoriaNoExistenteException("La categoria no existe");
        }
        return publicacionRepository.findAllByCategoria(categoria);
    }

    public Time obtenerHora(Long id){
        Optional<Publicacion> publicacion=publicacionRepository.findById(id);
        if (publicacion.isEmpty()){
            throw new PublicacionNoExisteException("La publicacion no existe");
        }
        Publicacion pubHora=publicacion.get();
        return pubHora.getHora();
    }

    public Date obtenerFecha(Long id){
        Optional<Publicacion> publicacion=publicacionRepository.findById(id);
        if (publicacion.isEmpty()){
            throw new PublicacionNoExisteException("La publicacion no existe");
        }
        Publicacion pubHora=publicacion.get();
        return pubHora.getFecha();
    }
}
