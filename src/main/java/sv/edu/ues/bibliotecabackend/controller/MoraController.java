package sv.edu.ues.bibliotecabackend.controller;


import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.bibliotecabackend.models.entity.Mora;
import sv.edu.ues.bibliotecabackend.service.MoraService;

@RestController
@RequestMapping("api/v1/mora")
public class MoraController {

    private final MoraService moraService;

    public MoraController(MoraService moraService) {
        this.moraService = moraService;
    }

    @GetMapping
    ResponseEntity<Mora> getMora() {
        return ResponseEntity.ok(moraService.getMora());
    }

    @PutMapping("/{id}")
    ResponseEntity<Mora> updateMora(@PathVariable Long id, @RequestBody @Valid Mora mora) {
        return ResponseEntity.ok(moraService.updateMora(id,mora.getMonto()));
    }
}
