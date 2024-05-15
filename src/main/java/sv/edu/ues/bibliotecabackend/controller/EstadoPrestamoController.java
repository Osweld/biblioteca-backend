package sv.edu.ues.bibliotecabackend.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.ues.bibliotecabackend.models.entity.EstadoPrestamo;
import sv.edu.ues.bibliotecabackend.models.repository.EstadoPrestamoRepository;

import java.util.List;

@RestController
@RequestMapping("api/v1/estado-prestamo")
public class EstadoPrestamoController {

    private final EstadoPrestamoRepository estadoPrestamoRepository;

    public EstadoPrestamoController(EstadoPrestamoRepository estadoPrestamoRepository) {
        this.estadoPrestamoRepository = estadoPrestamoRepository;
    }

    @GetMapping
    ResponseEntity<List<EstadoPrestamo>> findall() {
        return ResponseEntity.ok(estadoPrestamoRepository.findAll());
    }


}
