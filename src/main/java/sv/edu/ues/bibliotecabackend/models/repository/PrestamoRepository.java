package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sv.edu.ues.bibliotecabackend.models.entity.Prestamo;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    @Query("SELECT COUNT(p) FROM Prestamo p WHERE p.miembro.id = :idPersona AND p.estadoPrestamo.id IN (1,3)")
    Integer prestamosActualesByPersona(@Param("idPersona") Long idPersona);

    @Query("SELECT p FROM Prestamo p WHERE p.miembro.id = :id AND p.estadoPrestamo.id IN (1, 3)")
    List<Prestamo> findActiveByPersonaId(@Param("id") Long id);

    @Query("SELECT p FROM Prestamo p WHERE p.miembro.id = :id")
    Page<Prestamo> findHistorialByPersonaId(@Param("id") Long id, Pageable pageable);

}

