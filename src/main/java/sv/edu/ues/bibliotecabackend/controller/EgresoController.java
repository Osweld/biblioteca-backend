package sv.edu.ues.bibliotecabackend.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.bibliotecabackend.models.entity.Egreso;
import sv.edu.ues.bibliotecabackend.service.EgresoService;

@RestController
@RequestMapping("api/v1/egreso")
public class EgresoController {

    private final EgresoService egresoService;

    public EgresoController(EgresoService egresoService) {
        this.egresoService = egresoService;
    }

    @GetMapping
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Page<Egreso>> findAllEgreso(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(egresoService.findAllEgreso(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Egreso> findEgresoyId(@PathVariable Long id) {
        return ResponseEntity.ok(egresoService.findEgresoById(id));
    }

    @PostMapping
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Egreso> saveEgreso(@RequestBody @Valid Egreso egreso) {
        return new ResponseEntity<>(egresoService.saveEgreso(egreso), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Egreso> updateEgreso(@RequestBody @Valid Egreso egreso, @PathVariable Long id){
        return ResponseEntity.ok(egresoService.updateEgreso(id,egreso));
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Egreso> deleteEgreso(@PathVariable Long id){
        return ResponseEntity.ok(egresoService.deleteEgreso(id));
    }
}
