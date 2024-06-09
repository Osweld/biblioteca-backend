package sv.edu.ues.bibliotecabackend.controller;

import jakarta.annotation.security.PermitAll;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import sv.edu.ues.bibliotecabackend.models.dto.PasswordDTO;
import sv.edu.ues.bibliotecabackend.models.entity.Usuario;
import sv.edu.ues.bibliotecabackend.service.UsuarioService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping()
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Page<Usuario>> findAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(usuarioService.findAll(PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Usuario> findById(@PathVariable Long id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @GetMapping("/dui/{dui}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Usuario> findByDui(@PathVariable String dui) {
        return ResponseEntity.ok(usuarioService.findByPersonaDUI(dui));
    }

    @PostMapping()
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Usuario> save(@RequestBody @Valid Usuario usuario) {
        return new ResponseEntity(usuarioService.save(usuario), HttpStatus.CREATED);
    }

    @PutMapping("/password/{id}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Usuario> updatePassword(@PathVariable Long id, @RequestBody PasswordDTO passwordDTO) {
        return ResponseEntity.ok(usuarioService.updatePassword(id, passwordDTO));
    }

    @PutMapping("/{id}")
    @Secured({"ROLE_BIBLIOTECARIO"})
    ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        return ResponseEntity.ok(usuarioService.updateUsuario(id,usuario));
    }

    @GetMapping("reset-password/{email}")
    @PermitAll()
    ResponseEntity<Map<String, String>> resetPassword(@PathVariable String email) throws MessagingException {
        usuarioService.resetPassword(email);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Email enviado correctamente");
        return ResponseEntity.ok(response);
    }



}
