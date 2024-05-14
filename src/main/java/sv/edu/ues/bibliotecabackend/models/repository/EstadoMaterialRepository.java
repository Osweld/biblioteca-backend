package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.bibliotecabackend.models.entity.EstadoMaterial;

public interface EstadoMaterialRepository extends JpaRepository<EstadoMaterial, Long> {
}
