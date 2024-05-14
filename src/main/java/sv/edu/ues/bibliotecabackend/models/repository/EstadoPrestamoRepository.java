package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.bibliotecabackend.models.entity.EstadoPrestamo;

public interface EstadoPrestamoRepository extends JpaRepository<EstadoPrestamo, Long> {
}
