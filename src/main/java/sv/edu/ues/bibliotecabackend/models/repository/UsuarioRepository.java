package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sv.edu.ues.bibliotecabackend.models.entity.Usuario;
import sv.edu.ues.bibliotecabackend.models.projection.UsuarioWithoutPassword;


import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.persona.rol.id = 3")
    Page<Usuario> findAllByRol(Pageable pageable);

    @Query("SELECT u FROM Usuario u WHERE u.id = :id AND u.persona.rol.id = 3")
    Optional<Usuario> findByIdAndRol(Long id);

    @Query("SELECT u FROM Usuario u WHERE u.persona.DUI = :dui AND u.persona.rol.id = 3")
    Optional<Usuario> findByPersonaDUIAndRol(String dui);

    Optional<Usuario> findByUsername(String username);
    @Query("SELECT u FROM Usuario u WHERE u.persona.email = :email")
    Optional<Usuario> findUsuarioByEmail(String email);
}
