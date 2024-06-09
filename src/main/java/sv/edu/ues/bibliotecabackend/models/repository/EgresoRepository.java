package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sv.edu.ues.bibliotecabackend.models.entity.Egreso;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EgresoRepository extends JpaRepository<Egreso,Long> {

    @Query("SELECT SUM(e.monto) FROM Egreso e WHERE e.fechaPago BETWEEN :startDate AND :endDate")
    BigDecimal sumMontoBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
