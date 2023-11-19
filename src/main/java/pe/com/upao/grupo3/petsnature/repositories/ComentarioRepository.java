package pe.com.upao.grupo3.petsnature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.com.upao.grupo3.petsnature.models.Comentario;
import pe.com.upao.grupo3.petsnature.models.Publicacion;
import pe.com.upao.grupo3.petsnature.serializers.ComentarioSerializer;
import java.util.List;
import java.util.Optional;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario,Long> {
    @Query("SELECT c FROM Comentario c LEFT JOIN FETCH c.respuestas WHERE c.id = :comentarioId")
    public Optional<Comentario> obtenerComentarioConRespuestas(@Param("comentarioId") Long comentarioId);

    @Query("SELECT NEW pe.com.upao.grupo3.petsnature.serializers.ComentarioSerializer(c.fecha,c.contenido,c.usuario.nombre,c.usuario.imgPerfil) from Comentario c where c.publicacion=:publicacion")
    public List<ComentarioSerializer> findAllByPublicacion(@Param("publicacion") Publicacion publicacion);

    @Query("SELECT NEW pe.com.upao.grupo3.petsnature.serializers.ComentarioSerializer(c.fecha,c.contenido,c.usuario.nombre,c.usuario.imgPerfil) from Comentario c where c.comentarioPadre=:comentarioPadre")
    public List<ComentarioSerializer> findAllByComentarioPadre(@Param("comentarioPadre") Comentario comentarioPadre);
}
