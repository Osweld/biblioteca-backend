package sv.edu.ues.bibliotecabackend.service;

import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sv.edu.ues.bibliotecabackend.models.dto.PasswordDTO;
import sv.edu.ues.bibliotecabackend.models.entity.Persona;
import sv.edu.ues.bibliotecabackend.models.entity.Usuario;
import sv.edu.ues.bibliotecabackend.models.projection.UsuarioWithoutPassword;

import java.util.List;

public interface UsuarioService {

    Page<Usuario> findAll(Pageable pageable);
    Usuario findById(Long id);
    Usuario findByPersonaDUI(String dui);
    Usuario save(Usuario usuario);
    Usuario updateUsuario(Long id, Usuario usuario);
    Usuario updatePassword(Long id, PasswordDTO passwordDTO);
    void resetPassword(String email) throws MessagingException;


}
