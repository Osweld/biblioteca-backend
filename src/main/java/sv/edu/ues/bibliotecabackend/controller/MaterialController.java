package sv.edu.ues.bibliotecabackend.controller;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.bibliotecabackend.models.entity.Material;
import sv.edu.ues.bibliotecabackend.service.MaterialService;

@RestController
@RequestMapping("api/v1/material")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping()
    ResponseEntity<Page<Material>> getAllMaterials(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(required = false) Long idIdioma,
            @RequestParam(required = false) Long idAutor,
            @RequestParam(required = false) Long idCategoria,
            @RequestParam(required = false) Long idEstadoMaterial
    ) {
        return ResponseEntity.ok(
                materialService.getAllMaterials(
                        idIdioma,idAutor,idCategoria,idEstadoMaterial, PageRequest.of(page,size)));
    }

    @GetMapping("/{id}")
    ResponseEntity<Material> getMaterial(@PathVariable Long id) {
        return ResponseEntity.ok(materialService.getMaterialById(id));
    }

    @PostMapping
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Material> saveMaterial(@RequestBody @Valid Material material) {
        return new ResponseEntity<>(materialService.saveMaterial(material), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Material> updateMaterial(@PathVariable Long id, @RequestBody @Valid Material material) {
        return ResponseEntity.ok(materialService.updateMaterial(id, material));
    }
}
