package pe.com.upao.grupo3.petsnature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.upao.grupo3.petsnature.models.Tema;

public interface TemaRepository extends JpaRepository<Tema,String> {
}
