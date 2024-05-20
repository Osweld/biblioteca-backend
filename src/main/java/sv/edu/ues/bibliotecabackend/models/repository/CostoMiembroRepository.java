package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sv.edu.ues.bibliotecabackend.models.entity.CostoMiembro;
import sv.edu.ues.bibliotecabackend.models.entity.TipoPago;

import java.util.Optional;

public interface CostoMiembroRepository extends JpaRepository<CostoMiembro, Long> {


    Optional<CostoMiembro> findByTipoPago(TipoPago tipoPago);
}
