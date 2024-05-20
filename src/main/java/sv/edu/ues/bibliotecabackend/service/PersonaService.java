package sv.edu.ues.bibliotecabackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sv.edu.ues.bibliotecabackend.models.entity.Persona;

public interface PersonaService {

    Persona getPersonaById(Long id);
    Persona getPersonaByDUI(String dui);
    Persona getPersonaByEmail(String email);

    Page<Persona> getAllPersonasPagination(Pageable pageable);
    Persona savePersona(Persona persona);
    Persona updatePersona(Long id,Persona persona);
    Persona updateMembresiaPersona(Long id);
}
