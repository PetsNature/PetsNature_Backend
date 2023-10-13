package pe.com.upao.grupo3.Petsnature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.upao.grupo3.Petsnature.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,String> {
}
