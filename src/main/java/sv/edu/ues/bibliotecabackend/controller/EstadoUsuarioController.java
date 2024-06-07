package sv.edu.ues.bibliotecabackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.ues.bibliotecabackend.models.entity.EstadoUsuario;
import sv.edu.ues.bibliotecabackend.models.repository.EstadoUsuarioRepository;

import java.util.List;

@RestController
@RequestMapping("api/v1/estado-usuario")
public class EstadoUsuarioController {

    private final EstadoUsuarioRepository estadoUsuarioRepository;

    public EstadoUsuarioController(EstadoUsuarioRepository estadoUsuarioRepository) {
        this.estadoUsuarioRepository = estadoUsuarioRepository;
    }

    @GetMapping
    @Secured({"ROLE_BIBLIOTECARIO"})
    public ResponseEntity<List<EstadoUsuario>> findAll() {
        return ResponseEntity.ok(estadoUsuarioRepository.findAll());
    }
}
