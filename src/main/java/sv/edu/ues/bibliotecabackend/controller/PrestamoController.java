package sv.edu.ues.bibliotecabackend.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.bibliotecabackend.models.entity.Prestamo;
import sv.edu.ues.bibliotecabackend.models.repository.PrestamoRepository;
import sv.edu.ues.bibliotecabackend.service.PrestamoService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/prestamo")
public class PrestamoController {

    private final PrestamoService prestamoService;

    public PrestamoController(PrestamoService prestamoService) {
        this.prestamoService = prestamoService;
    }

    @GetMapping
    @Secured({"ROLE_BIBLIOTECARIO"})
    public ResponseEntity<Page<Prestamo>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        Page<Prestamo> prestamos = prestamoService.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(prestamos);
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    public ResponseEntity<Prestamo> getPrestamo(@PathVariable Long id) {
        Prestamo prestamo = prestamoService.getPrestamo(id);
        return prestamo != null ? ResponseEntity.ok(prestamo) : ResponseEntity.notFound().build();
    }

    @GetMapping("/activos/{idPersona}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    public ResponseEntity<List<Prestamo>> activePrestamoByPersonaId(@PathVariable Long idPersona) {
        List<Prestamo> prestamos = prestamoService.activePrestamoByPersonaId(idPersona);
        return ResponseEntity.ok(prestamos);
    }

    @GetMapping("/historial/{idPersona}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    public ResponseEntity<Page<Prestamo>> historialPrestamoByPersonaId(@PathVariable Long idPersona, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        Page<Prestamo> prestamos = prestamoService.historialPrestamoBypersonaId(idPersona, PageRequest.of(page, size));
        return ResponseEntity.ok(prestamos);
    }

    @PostMapping
    @Secured({"ROLE_BIBLIOTECARIO"})
    public ResponseEntity<Prestamo> savePrestamo(@RequestBody @Valid Prestamo prestamo) {
        Prestamo savedPrestamo = prestamoService.savePrestamo(prestamo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPrestamo);
    }

    @PostMapping("/devolucion")
    @Secured({"ROLE_BIBLIOTECARIO"})
    public ResponseEntity<Prestamo> devolucion(@RequestBody Prestamo prestamo) {
        Prestamo updatedPrestamo = prestamoService.devolucion(prestamo);
        return ResponseEntity.ok(updatedPrestamo);
    }

    @PostMapping("devolucion-sin-material")
    @Secured({"ROLE_BIBLIOTECARIO"})
    public ResponseEntity<Prestamo> devolucionSinMaterial(@RequestBody Prestamo prestamo) {
        Prestamo updatedPrestamo = prestamoService.devolucionSinMaterial(prestamo);
        return ResponseEntity.ok(updatedPrestamo);
    }
    @PostMapping("/renovar/{id}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    public ResponseEntity<Prestamo> renovarPrestamo(@PathVariable Long id) {
        Prestamo renovado = prestamoService.renovarPrestamo(id);
        return renovado != null ? ResponseEntity.ok(renovado) : ResponseEntity.notFound().build();
    }


    @GetMapping("/mora/{id}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<BigDecimal> getMoraByPrestamoId(@PathVariable Long id){
        return ResponseEntity.ok(prestamoService.moraGenerada(id));
    }

    @GetMapping("/perdida/{id}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<BigDecimal> getMontoPerdidaMaterialByPrestamoId(@PathVariable Long id){
        return ResponseEntity.ok(prestamoService.montoPorPerdida(id));
    }
}

