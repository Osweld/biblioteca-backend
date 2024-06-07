package sv.edu.ues.bibliotecabackend.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.bibliotecabackend.models.entity.Autor;
import sv.edu.ues.bibliotecabackend.service.AutorService;

import java.util.List;

@RestController
@RequestMapping("api/v1/autor")
public class AutorController {

    private final AutorService autorService;

    public AutorController(AutorService autorService) {
        this.autorService = autorService;
    }

    //@Secured({"ROLE_ADMIN","ROLE_MANAGER"})
    @GetMapping("/{id}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Autor> getAutor(@PathVariable Long id) {
        return ResponseEntity.ok(autorService.getAutor(id));
    }

    @GetMapping("/pagination")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Page<Autor>> getAllAutorByPagination(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size){
        return ResponseEntity.ok(autorService.getAutorsByPagination(PageRequest.of(page, size)));
    }

    @GetMapping()
    @PermitAll()
    ResponseEntity<List<Autor>> getAllAutor(){
        return ResponseEntity.ok(autorService.getAllAutor());
    }

    @GetMapping("/nombre/{nombre}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<List<Autor>> allAutorByNombre(@PathVariable String nombre){
        return ResponseEntity.ok(autorService.getAutorByText(nombre));
    }

    @PostMapping()
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Autor> createAutor(@RequestBody @Valid Autor autor) {
        return new ResponseEntity<>(autorService.saveAutor(autor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Autor> updateAutor(@PathVariable Long id, @RequestBody @Valid Autor autor) {
        return new ResponseEntity<>(autorService.updateAutor(id, autor), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Autor> deleteAutor(@PathVariable Long id) {
        return ResponseEntity.ok(autorService.deleteAutor(id));
    }
}
