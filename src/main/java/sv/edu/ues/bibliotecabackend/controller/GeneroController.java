package sv.edu.ues.bibliotecabackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.ues.bibliotecabackend.models.entity.Genero;
import sv.edu.ues.bibliotecabackend.models.repository.GeneroRepository;

import java.util.List;

@RestController
@RequestMapping("api/v1/genero")
public class GeneroController {

    private final GeneroRepository generoRepository;

    public GeneroController(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    @GetMapping
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<List<Genero>> findAll() {
        return ResponseEntity.ok(generoRepository.findAll());
    }
}
