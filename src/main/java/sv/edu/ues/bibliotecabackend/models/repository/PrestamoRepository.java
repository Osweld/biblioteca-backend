package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.bibliotecabackend.models.entity.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
}
