package sv.edu.ues.bibliotecabackend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.ues.bibliotecabackend.models.entity.EstadoMaterial;
import sv.edu.ues.bibliotecabackend.models.repository.EstadoMaterialRepository;

import java.util.List;

@RestController
@RequestMapping("api/v1/estado-material")
public class EstadoMaterialController {


    private final EstadoMaterialRepository estadoMaterialRepository;

    public EstadoMaterialController(EstadoMaterialRepository estadoMaterialRepository) {
        this.estadoMaterialRepository = estadoMaterialRepository;
    }

    @GetMapping
    ResponseEntity<List<EstadoMaterial>> findAll() {
        return ResponseEntity.ok(estadoMaterialRepository.findAll());
    }
}
