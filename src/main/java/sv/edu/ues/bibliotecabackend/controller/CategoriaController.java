package sv.edu.ues.bibliotecabackend.controller;

import jakarta.annotation.security.PermitAll;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.ues.bibliotecabackend.models.entity.Categoria;
import sv.edu.ues.bibliotecabackend.models.repository.CategoriaRepository;

import java.util.List;

@RestController
@RequestMapping("api/v1/categoria")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    @PermitAll()
    ResponseEntity<List<Categoria>> findAll() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }
}
