package sv.edu.ues.bibliotecabackend.controller;


import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.ues.bibliotecabackend.models.entity.Idioma;
import sv.edu.ues.bibliotecabackend.models.repository.IdiomaRepository;

import java.util.List;

@RestController
@RequestMapping("api/v1/idioma")
public class IdiomaController {

    private final IdiomaRepository idiomaRepository;

    public IdiomaController(IdiomaRepository idiomaRepository) {
        this.idiomaRepository = idiomaRepository;
    }


    @GetMapping
    @PermitAll()
    ResponseEntity<List<Idioma>> findAll() {
        return ResponseEntity.ok(idiomaRepository.findAll());
    }
}
