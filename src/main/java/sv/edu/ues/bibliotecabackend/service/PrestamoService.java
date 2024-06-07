package sv.edu.ues.bibliotecabackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sv.edu.ues.bibliotecabackend.models.entity.EstadoPrestamo;
import sv.edu.ues.bibliotecabackend.models.entity.Prestamo;

import java.math.BigDecimal;
import java.util.List;

public interface PrestamoService {

    Page<Prestamo> findAll(Pageable pageable);
    Prestamo getPrestamo(Long id);
    List<Prestamo> activePrestamoByPersonaId(Long id);
    Page<Prestamo> historialPrestamoBypersonaId(Long id, Pageable pageable);
    Prestamo savePrestamo(Prestamo prestamo);
    Prestamo devolucion(Prestamo prestamo);
    Prestamo devolucionSinMaterial(Prestamo prestamo);
    Prestamo renovarPrestamo(Long id);
    BigDecimal moraGenerada(Long id);
    BigDecimal montoPorPerdida(Long id);

}
