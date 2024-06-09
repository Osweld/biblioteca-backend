package sv.edu.ues.bibliotecabackend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.ues.bibliotecabackend.models.dto.EgresoDatosDTO;
import sv.edu.ues.bibliotecabackend.models.dto.EstadisticasDTO;
import sv.edu.ues.bibliotecabackend.models.dto.PagoDatosDTO;
import sv.edu.ues.bibliotecabackend.models.dto.PrestamoDiaDTO;
import sv.edu.ues.bibliotecabackend.service.DashboardService;

import java.util.List;

@RestController()
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/basic")
    ResponseEntity<EstadisticasDTO> getEstadisticas() {
        return ResponseEntity.ok(dashboardService.getBasicStatistics());
    }

    @GetMapping("/prestamos-ultima-semana")
    ResponseEntity<List<PrestamoDiaDTO>> getPrestamosPorDiaUltimaSemana() {
        return ResponseEntity.ok(dashboardService.getPrestamosPorDiaUltimaSemana());
    }

    @GetMapping("/pagos")
    ResponseEntity<PagoDatosDTO> getPagos() {
        return ResponseEntity.ok(dashboardService.getPagoDatos());
    }

    @GetMapping("/egresos")
    ResponseEntity<EgresoDatosDTO> getEgresos() {
        return ResponseEntity.ok(dashboardService.getEgresoDatos());
    }


}
