package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.bibliotecabackend.models.entity.Persona;
import sv.edu.ues.bibliotecabackend.models.entity.Rol;

import java.util.Optional;

public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByDUI(String dui);
    Optional<Persona> findByEmail(String email);
    Optional<Persona> findByRol(Rol rol);
}
