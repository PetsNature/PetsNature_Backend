package pe.com.upao.grupo3.petsnature.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upao.grupo3.petsnature.exceptions.CategoriaNoExistenteException;
import pe.com.upao.grupo3.petsnature.exceptions.PublicacionNoExisteException;
import pe.com.upao.grupo3.petsnature.models.Publicacion;
import pe.com.upao.grupo3.petsnature.models.RazaAnimal;
import pe.com.upao.grupo3.petsnature.models.TipoMascota;
import pe.com.upao.grupo3.petsnature.models.Usuario;
import pe.com.upao.grupo3.petsnature.repositories.PublicacionRepository;
import pe.com.upao.grupo3.petsnature.repositories.RazaAnimalRepository;
import pe.com.upao.grupo3.petsnature.repositories.TipoMascotaRepository;
import pe.com.upao.grupo3.petsnature.serializers.PublicacionSerializer;

import java.sql.Time;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PublicacionService {
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Autowired
    private TipoMascotaRepository tipoMascotaRepository;

    @Autowired
    private RazaAnimalRepository razaAnimalRepository;

    public Publicacion crearPublicacion(Publicacion publicacion){
        return publicacionRepository.save(publicacion);
    }

    public List<PublicacionSerializer> filtrarporCategoria(String categoria){
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

    public List<PublicacionSerializer> verMisPublicaciones(Usuario usuario){
        return publicacionRepository.findAllByUsuario(usuario);
    }

    public Publicacion encontrarPublicacion(Long id){
        Publicacion publicacion=publicacionRepository.findById(id).orElse(null);
        if (publicacion==null){
            throw new PublicacionNoExisteException("La publicacion no existe");
        }
        return publicacion;
    }

    @Transactional
    public int reaccionarPublicacion(Long id){
        Publicacion publicacion=publicacionRepository.findById(id).orElse(null);
        int reacciones=publicacion.getReacciones()+1;
        publicacion.setReacciones(reacciones);
        return reacciones;
    }

    public List<PublicacionSerializer> filtrarPorTipoMascota(String categoria,String tipoMascota){
        TipoMascota tipoMascota1=tipoMascotaRepository.findById(tipoMascota).orElse(null);
        return publicacionRepository.findAllByTipoMascota(categoria,tipoMascota1);
    }

    public List<PublicacionSerializer> filtrarPorTipoMascotaYRaza(String categoria,String tipoMascota, String razaAnimal){
        TipoMascota tipoMascota1=tipoMascotaRepository.findById(tipoMascota).orElse(null);
        RazaAnimal razaAnimal1=razaAnimalRepository.findById(razaAnimal).orElse(null);
        return publicacionRepository.findAllByTipoMascotaAndRaza(categoria,tipoMascota1,razaAnimal1);
    }
}