package sv.edu.ues.bibliotecabackend.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.ues.bibliotecabackend.models.entity.Pago;
import sv.edu.ues.bibliotecabackend.models.repository.PagoRepository;

@RestController
@RequestMapping("/api/v1/pago")
public class PagoController {


    private final PagoRepository pagoRepository;

    public PagoController(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }


    @GetMapping
    ResponseEntity<Page<Pago>> findAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(pagoRepository.findAll(PageRequest.of(page, size)));
    }
}
