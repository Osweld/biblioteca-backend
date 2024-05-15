package sv.edu.ues.bibliotecabackend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.ues.bibliotecabackend.models.entity.Rol;
import sv.edu.ues.bibliotecabackend.models.repository.RolRepository;

import java.util.List;

@RestController
@RequestMapping("api/v1/rol")
public class RolController {

    private final RolRepository rolRepository;
    public RolController(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @GetMapping
    ResponseEntity<List<Rol>> findAll() {
        return ResponseEntity.ok(rolRepository.findAll());
    }
}
