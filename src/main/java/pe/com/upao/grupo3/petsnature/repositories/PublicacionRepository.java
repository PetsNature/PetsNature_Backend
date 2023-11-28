package pe.com.upao.grupo3.petsnature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.com.upao.grupo3.petsnature.models.*;
import pe.com.upao.grupo3.petsnature.serializers.PublicacionSerializer;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion,Long> {
    @Query("SELECT NEW pe.com.upao.grupo3.petsnature.serializers.PublicacionSerializer(p.fecha,p.hora,p.contenido,p.img,p.enlace,p.usuario.nombre,p.usuario.imgPerfil,p.reacciones) FROM Publicacion p WHERE p.categoria=:categoria")
    public List<PublicacionSerializer> findAllByCategoria(@Param("categoria") String categoria);

    @Query("SELECT NEW pe.com.upao.grupo3.petsnature.serializers.PublicacionSerializer(p.fecha,p.hora,p.contenido,p.img,p.enlace,p.usuario.nombre,p.usuario.imgPerfil,p.reacciones) FROM Publicacion p WHERE p.usuario=:usuario")
    public List<PublicacionSerializer> findAllByUsuario(@Param("usuario") Usuario usuario);

    @Query("SELECT NEW pe.com.upao.grupo3.petsnature.serializers.PublicacionSerializer(p.fecha,p.hora,p.contenido,p.img,p.enlace,p.usuario.nombre,p.usuario.imgPerfil,p.reacciones) FROM Publicacion p WHERE p.categoria=:categoria and p.tipoMascota=:tipoMascota")
    public List<PublicacionSerializer> findAllByTipoMascota(@Param("categoria") String categoria, @Param("tipoMascota") TipoMascota tipoMascota);

    @Query("SELECT NEW pe.com.upao.grupo3.petsnature.serializers.PublicacionSerializer(p.fecha,p.hora,p.contenido,p.img,p.enlace,p.usuario.nombre,p.usuario.imgPerfil,p.reacciones) FROM Publicacion p WHERE p.categoria=:categoria and p.tipoMascota=:tipoMascota and p.raza=:raza")
    public List<PublicacionSerializer> findAllByTipoMascotaAndRaza(@Param("categoria") String categoria, @Param("tipoMascota") TipoMascota tipoMascota, @Param("raza") RazaAnimal razaAnimal);

    @Query("SELECT NEW pe.com.upao.grupo3.petsnature.serializers.PublicacionSerializer(p.fecha,p.hora,p.contenido,p.img,p.enlace,p.usuario.nombre,p.usuario.imgPerfil,p.reacciones) FROM Publicacion p WHERE p.categoria=:categoria and p.tipoMascota=:tipoMascota and p.tema=:tema")
    public List<PublicacionSerializer> findAllByTemaAndTipoMascota(@Param("categoria") String categoria,@Param("tipoMascota") TipoMascota tipoMascota,@Param("tema") Tema tema);

    @Query("SELECT NEW pe.com.upao.grupo3.petsnature.serializers.PublicacionSerializer(p.fecha,p.hora,p.contenido,p.img,p.enlace,p.usuario.nombre,p.usuario.imgPerfil,p.reacciones) FROM Publicacion p WHERE p.categoria=:categoria and p.tipoMascota=:tipoMascota and p.raza=:raza and p.tema=:tema")
    public List<PublicacionSerializer> findAllByCategoriaAndTipoMascotaAndRazaAndTema(@Param("categoria") String categoria, @Param("tipoMascota") TipoMascota tipoMascota, @Param("raza") RazaAnimal razaAnimal, @Param("tema") Tema tema);

    @Query("SELECT NEW pe.com.upao.grupo3.petsnature.serializers.PublicacionSerializer(p.fecha,p.hora,p.contenido,p.img,p.enlace,p.usuario.nombre,p.usuario.imgPerfil,p.reacciones) FROM Publicacion p WHERE p.categoria=:categoria and p.tema=:tema")
    public List<PublicacionSerializer> findAllByTema(@Param("categoria") String categoria,@Param("tema") Tema tema);
}

