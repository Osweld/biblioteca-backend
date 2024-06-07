package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sv.edu.ues.bibliotecabackend.models.entity.Rol;

import java.util.List;

public interface RolRepository extends JpaRepository<Rol, Long> {

    @Query("SELECT r FROM Rol r WHERE r.rol IN ('MIEMBRO', 'PROFESOR')")
    List<Rol> findRolesUsuarios();

    @Query("SELECT r FROM Rol r WHERE r.rol IN ('BIBLIOTECARIO')")
    List<Rol> findRolesAdministracion();
}
