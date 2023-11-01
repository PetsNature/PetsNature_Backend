package pe.com.upao.grupo3.Petsnature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.upao.grupo3.Petsnature.models.Publicacion;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion,Long> {

    public List<Publicacion> findAllByCategoria(String categoria);
}
