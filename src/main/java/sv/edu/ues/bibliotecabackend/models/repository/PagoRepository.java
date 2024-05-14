package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.bibliotecabackend.models.entity.Pago;

public interface PagoRepository extends JpaRepository<Pago, Long> {
}
