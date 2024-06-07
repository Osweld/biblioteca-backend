package sv.edu.ues.bibliotecabackend.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.bibliotecabackend.models.entity.CostoMiembro;
import sv.edu.ues.bibliotecabackend.service.CostoMiembroService;

@RestController
@RequestMapping("api/v1/mora")
public class CostoMiembroController {

    private final CostoMiembroService costoMiembroService;

    public CostoMiembroController(CostoMiembroService costoMiembroService) {
        this.costoMiembroService = costoMiembroService;
    }

    @GetMapping("tipo-pago/{idTipoPago}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<CostoMiembro> getCostoMiembroByTipoPago(@PathVariable Long idTipoPago) {
        return ResponseEntity.ok(costoMiembroService.getCostoMiembroByTipoPago(idTipoPago));
    }

    @PutMapping("tipo-pago/{idTipoPago}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<CostoMiembro> updateMora(@PathVariable Long idTipoPago, @RequestBody @Valid CostoMiembro costoMiembro) {
        return ResponseEntity.ok(costoMiembroService.updateCostoMiembroByTipopago(idTipoPago,costoMiembro));
    }
}
