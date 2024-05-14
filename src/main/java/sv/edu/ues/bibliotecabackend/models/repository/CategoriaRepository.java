package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.bibliotecabackend.models.entity.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
}
