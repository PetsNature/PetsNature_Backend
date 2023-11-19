package pe.com.upao.grupo3.petsnature.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.com.upao.grupo3.petsnature.models.TipoMascota;

public interface TipoMascotaRepository extends JpaRepository<TipoMascota,String> {
}
