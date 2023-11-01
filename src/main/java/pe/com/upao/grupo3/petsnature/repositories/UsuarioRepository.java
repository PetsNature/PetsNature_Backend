package pe.com.upao.grupo3.petsnature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.upao.grupo3.petsnature.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {
}
