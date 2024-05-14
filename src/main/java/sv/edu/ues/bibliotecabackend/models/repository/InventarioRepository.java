package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.bibliotecabackend.models.entity.Inventario;

public interface InventarioRepository extends JpaRepository<Inventario, Long> {
}
