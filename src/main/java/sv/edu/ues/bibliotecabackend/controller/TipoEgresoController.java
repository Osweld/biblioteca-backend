package sv.edu.ues.bibliotecabackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.ues.bibliotecabackend.models.entity.TipoEgreso;
import sv.edu.ues.bibliotecabackend.models.repository.TipoEgresoRepository;

import java.util.List;

@RestController
@RequestMapping("api/v1/tipo-egreso")
public class TipoEgresoController {

    private final TipoEgresoRepository tipoEgresoRepository;
    public TipoEgresoController(TipoEgresoRepository tipoEgresoRepository) {
        this.tipoEgresoRepository = tipoEgresoRepository;
    }

    @GetMapping
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<List<TipoEgreso>> findAll() {
        return ResponseEntity.ok(tipoEgresoRepository.findAll());
    }
}
