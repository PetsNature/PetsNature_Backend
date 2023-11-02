package pe.com.upao.grupo3.petsnature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.com.upao.grupo3.petsnature.models.Publicacion;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion,Long> {
    @Query("SELECT p, u FROM Publicacion p JOIN p.usuario u WHERE p.categoria = ?1")
    List<Object[]> findAllByCategoria(String categoria);

}
