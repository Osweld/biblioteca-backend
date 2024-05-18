package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.bibliotecabackend.models.entity.Inventario;
import sv.edu.ues.bibliotecabackend.models.entity.Material;

import java.util.Optional;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    Optional<Inventario> findInventarioByMaterial(Material material);
}
