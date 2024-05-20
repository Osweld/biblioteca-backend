package sv.edu.ues.bibliotecabackend.models.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.bibliotecabackend.models.entity.TipoEgreso;

public interface TipoEgresoRepository extends JpaRepository<TipoEgreso, Long> {
}
