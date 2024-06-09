package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sv.edu.ues.bibliotecabackend.models.dto.PrestamoDiaDTO;
import sv.edu.ues.bibliotecabackend.models.entity.Prestamo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {

    @Query("SELECT COUNT(p) FROM Prestamo p WHERE p.miembro.id = :idPersona AND p.estadoPrestamo.id IN (1,3)")
    Integer prestamosActualesByPersona(@Param("idPersona") Long idPersona);

    @Query("SELECT p FROM Prestamo p WHERE p.miembro.id = :id AND p.estadoPrestamo.id IN (1, 3)")
    List<Prestamo> findActiveByPersonaId(@Param("id") Long id);

    @Query("SELECT p FROM Prestamo p WHERE p.estadoPrestamo.id = 1")
    List<Prestamo> findAllActivePrestamos();

    @Query("SELECT p FROM Prestamo p WHERE p.miembro.id = :id")
    Page<Prestamo> findHistorialByPersonaId(@Param("id") Long id, Pageable pageable);

    @Query("SELECT p FROM Prestamo p WHERE p.fechaEntrega = :fechaEntrega AND p.estadoPrestamo.id = 1")
    List<Prestamo> findPrestamosByFechaEntrega(@Param("fechaEntrega") LocalDate fechaEntrega);

    @Query("SELECT p FROM Prestamo p WHERE p.fechaEntrega = :fechaEntrega AND p.estadoPrestamo.id = 3")
    List<Prestamo> findPrestamosByFechaEntregaRetraso(@Param("fechaEntrega") LocalDate fechaEntrega);

    @Query("SELECT p FROM Prestamo p WHERE p.fechaEntrega BETWEEN :start AND :end AND p.estadoPrestamo.id = 1")
    List<Prestamo> findPrestamosByFechaEntregaBetween(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT p FROM Prestamo p WHERE p.fechaEntrega BETWEEN :start AND :end AND p.estadoPrestamo.id = 3")
    List<Prestamo> findPrestamosByFechaEntregaBetweenRetraso(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

    @Query("SELECT COUNT(p) FROM Prestamo p WHERE p.estadoPrestamo.id IN (1, 3)")
    Integer countPrestamosByEstadosActivos();

    @Query("SELECT COUNT(p) FROM Prestamo p WHERE p.estadoPrestamo.id IN (2, 4)")
    Integer countPrestamosByEstadosRealizados();

    @Query("SELECT new sv.edu.ues.bibliotecabackend.models.dto.PrestamoDiaDTO(CAST(p.fechaInicio AS LocalDate), COUNT(p)) " +
            "FROM Prestamo p " +
            "WHERE p.fechaInicio >= :lastWeek " +
            "GROUP BY CAST(p.fechaInicio AS LocalDate)")
    List<PrestamoDiaDTO> countPrestamosByDay(@Param("lastWeek") LocalDateTime lastWeek);

}

