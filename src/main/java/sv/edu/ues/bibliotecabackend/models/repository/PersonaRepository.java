package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sv.edu.ues.bibliotecabackend.models.entity.Persona;
import sv.edu.ues.bibliotecabackend.models.entity.Rol;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    @Query("SELECT p FROM Persona p WHERE p.id = :id AND (p.rol.id = 1 OR p.rol.id = 2)")
    Optional<Persona> findByIdAndRoles(@Param("id") Long id);

    @Query("SELECT p FROM Persona p WHERE p.DUI = :dui AND (p.rol.id = 1 OR p.rol.id = 2)")
    Optional<Persona> findByDUIAndRoles(@Param("dui") String dui);

    @Query("SELECT p FROM Persona p WHERE p.email = :email AND (p.rol.id = 1 OR p.rol.id = 2)")
    Optional<Persona> findByEmailAndRoles(@Param("email") String email);

    @Query("SELECT p FROM Persona p WHERE p.rol.id = 1 OR p.rol.id = 2")
    Page<Persona> findAllByRoles(Pageable pageable);

    @Query("SELECT COUNT(p) FROM Persona p WHERE p.rol.id IN (1, 2)")
    Integer countByRoles();

    @Query("SELECT p FROM Persona p WHERE p.expiracionMembresia = :yesterday AND (p.rol.id = 1 OR p.rol.id = 2)")
    List<Persona> findByExpiracionMembresiaYesterday(@Param("yesterday") LocalDate yesterday);
}
