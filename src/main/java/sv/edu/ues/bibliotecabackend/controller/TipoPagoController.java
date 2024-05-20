package sv.edu.ues.bibliotecabackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.ues.bibliotecabackend.models.entity.TipoPago;
import sv.edu.ues.bibliotecabackend.models.repository.TipoPagoRepository;

import java.util.List;

@RestController
@RequestMapping("api/v1/tipo-pago")
public class TipoPagoController {

    private final TipoPagoRepository tipoPagoRepository;

    public TipoPagoController(TipoPagoRepository tipoPagoRepository) {
        this.tipoPagoRepository = tipoPagoRepository;
    }

    @GetMapping
    ResponseEntity<List<TipoPago>> findAll(){
        return ResponseEntity.ok(tipoPagoRepository.findAll());
    }
}
