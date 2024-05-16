package sv.edu.ues.bibliotecabackend.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    ResponseEntity<Page<Egreso>> findAllEgreso(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(egresoService.findAllEgreso(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    ResponseEntity<Egreso> findEgresoyId(@PathVariable Long id) {
        return ResponseEntity.ok(egresoService.findEgresoById(id));
    }

    @PostMapping
    ResponseEntity<Egreso> saveEgreso(@RequestBody @Valid Egreso egreso) {
        return new ResponseEntity<>(egresoService.saveEgreso(egreso), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    ResponseEntity<Egreso> updateEgreso(@RequestBody @Valid Egreso egreso, @PathVariable Long id){
        return ResponseEntity.ok(egresoService.updateEgreso(id,egreso));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Egreso> deleteEgreso(@PathVariable Long id){
        return ResponseEntity.ok(egresoService.deleteEgreso(id));
    }
}
