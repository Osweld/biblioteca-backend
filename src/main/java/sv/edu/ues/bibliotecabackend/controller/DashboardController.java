package sv.edu.ues.bibliotecabackend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<EstadisticasDTO> getEstadisticas() {
        return ResponseEntity.ok(dashboardService.getBasicStatistics());
    }

    @GetMapping("/prestamos-ultima-semana")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<List<PrestamoDiaDTO>> getPrestamosPorDiaUltimaSemana() {
        return ResponseEntity.ok(dashboardService.getPrestamosPorDiaUltimaSemana());
    }

    @GetMapping("/pagos")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<PagoDatosDTO> getPagos() {
        return ResponseEntity.ok(dashboardService.getPagoDatos());
    }

    @GetMapping("/egresos")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<EgresoDatosDTO> getEgresos() {
        return ResponseEntity.ok(dashboardService.getEgresoDatos());
    }


}
