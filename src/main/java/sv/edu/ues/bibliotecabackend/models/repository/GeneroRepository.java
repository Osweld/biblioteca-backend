package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.bibliotecabackend.models.entity.Genero;

public interface GeneroRepository extends JpaRepository<Genero, Long> {
}
