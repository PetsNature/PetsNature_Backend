package pe.com.upao.grupo3.petsnature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.com.upao.grupo3.petsnature.models.Publicacion;
import pe.com.upao.grupo3.petsnature.models.Usuario;
import pe.com.upao.grupo3.petsnature.serializers.PublicacionSerializer;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion,Long> {
    @Query("SELECT NEW pe.com.upao.grupo3.petsnature.serializers.PublicacionSerializer(p.fecha,p.hora,p.contenido,p.img,p.enlace,p.usuario.nombre,p.usuario.imgPerfil) FROM Publicacion p WHERE p.categoria=:categoria")
    List<PublicacionSerializer> findAllByCategoria(@Param("categoria") String categoria);

    @Query("SELECT NEW pe.com.upao.grupo3.petsnature.serializers.PublicacionSerializer(p.fecha,p.hora,p.contenido,p.img,p.enlace,p.usuario.nombre,p.usuario.imgPerfil) FROM Publicacion p WHERE p.usuario=:usuario")
    List<PublicacionSerializer> findAllByUsuario(@Param("usuario") Usuario usuario);
}
