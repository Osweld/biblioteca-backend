package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.bibliotecabackend.models.entity.Idioma;

public interface IdiomaRepository extends JpaRepository<Idioma,Long> {
}
