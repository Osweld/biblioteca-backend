package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.bibliotecabackend.models.entity.Egreso;

public interface EgresoRepository extends JpaRepository<Egreso,Long> {
}
