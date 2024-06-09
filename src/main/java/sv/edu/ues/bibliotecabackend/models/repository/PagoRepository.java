package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sv.edu.ues.bibliotecabackend.models.entity.Pago;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public interface PagoRepository extends JpaRepository<Pago, Long> {


    @Query("SELECT SUM(p.monto) FROM Pago p WHERE p.fechaPago BETWEEN :startDate AND :endDate")
    BigDecimal sumMontoBetweenDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
