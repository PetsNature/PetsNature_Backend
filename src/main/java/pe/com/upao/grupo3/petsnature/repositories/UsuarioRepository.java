package pe.com.upao.grupo3.petsnature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.upao.grupo3.petsnature.models.Usuario;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    public Optional<Usuario> findByCorreo(String correo);
}
