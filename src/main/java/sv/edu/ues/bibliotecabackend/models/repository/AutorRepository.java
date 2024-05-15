package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.bibliotecabackend.models.entity.Autor;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor,Long> {

    List<Autor> findByAutorContainingIgnoreCase(String nombre);
}
