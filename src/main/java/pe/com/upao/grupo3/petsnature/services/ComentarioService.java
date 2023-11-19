package pe.com.upao.grupo3.petsnature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.upao.grupo3.petsnature.exceptions.ComentarioNoExisteException;
import pe.com.upao.grupo3.petsnature.models.Comentario;
import pe.com.upao.grupo3.petsnature.models.Publicacion;
import pe.com.upao.grupo3.petsnature.repositories.ComentarioRepository;
import pe.com.upao.grupo3.petsnature.serializers.ComentarioSerializer;

import java.util.List;

@Service
public class ComentarioService {
    @Autowired
    private ComentarioRepository comentarioRepository;

    public Comentario comentar(Comentario comentario) {
        return comentarioRepository.save(comentario);
    }

    public List<Comentario> verRespuesta(Long id) {
        Comentario comentario = comentarioRepository.findById(id).orElse(null);
        if (comentario == null) {
            throw new ComentarioNoExisteException("Comentario no existe");
        }
        return comentario.getRespuestas();
    }

    public List<ComentarioSerializer> findAllByPublicacion(Publicacion publicacion) {
        return comentarioRepository.findAllByPublicacion(publicacion);
    }

    public Comentario findById(Long id) {
        Comentario comentario = comentarioRepository.findById(id).orElse(null);

        if (comentario == null) {
            throw new ComentarioNoExisteException("El comentario no existe");
        }
        return comentario;
    }

    public List<ComentarioSerializer> findAllByComentarioPadre(Comentario comentario){
        return comentarioRepository.findAllByComentarioPadre(comentario);
    }
}
