package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.bibliotecabackend.models.entity.Mora;

public interface MoraRepository extends JpaRepository<Mora, Long> {
}
