package pe.com.upao.grupo3.petsnature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.com.upao.grupo3.petsnature.models.Tema;
import pe.com.upao.grupo3.petsnature.models.Usuario;
import pe.com.upao.grupo3.petsnature.serializers.InicioSesionSerializer;
import pe.com.upao.grupo3.petsnature.serializers.PublicacionSerializer;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    public Optional<Usuario> findByCorreo(String correo);

    @Query("SELECT NEW pe.com.upao.grupo3.petsnature.serializers.InicioSesionSerializer(u.correo, u.contrasena) FROM Usuario u")
    public List<InicioSesionSerializer> listarUsuarios();
}
