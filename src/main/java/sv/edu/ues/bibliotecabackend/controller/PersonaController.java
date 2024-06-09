package sv.edu.ues.bibliotecabackend.controller;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.bibliotecabackend.models.entity.Persona;
import sv.edu.ues.bibliotecabackend.service.PersonaService;

@RestController
@RequestMapping("api/v1/persona")
public class PersonaController {

    private final PersonaService personaService;


    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping()
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Page<Persona>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(personaService.getAllPersonasPagination(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Persona> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(personaService.getPersonaById(id));
    }

    @GetMapping("dui/{dui}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Persona> findByDui(@PathVariable("dui") String dui) {
        return ResponseEntity.ok(personaService.getPersonaByDUI(dui));
    }

    @GetMapping("email/{email}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Persona> findByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(personaService.getPersonaByEmail(email));
    }

    @PostMapping()
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Persona> save(@RequestBody @Valid Persona persona) throws MessagingException {
        return new ResponseEntity<>(personaService.savePersona(persona), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Persona> update(@PathVariable("id") Long id, @RequestBody @Valid Persona persona) {
        return new ResponseEntity<>(personaService.updatePersona(id, persona), HttpStatus.OK);
    }

    @PutMapping("membresia/{id}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Persona> updateMembresia(@PathVariable("id") Long id) {
        return ResponseEntity.ok(personaService.updateMembresiaPersona(id));
    }

}
