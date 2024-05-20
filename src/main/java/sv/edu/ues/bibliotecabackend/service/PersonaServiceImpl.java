package sv.edu.ues.bibliotecabackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.ues.bibliotecabackend.exceptions.PersonaAlreadyExistsException;
import sv.edu.ues.bibliotecabackend.models.entity.EstadoUsuario;
import sv.edu.ues.bibliotecabackend.models.entity.Persona;
import sv.edu.ues.bibliotecabackend.models.repository.PersonaRepository;
import java.time.LocalDate;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Persona getPersonaById(Long id) {
        return personaRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public Persona getPersonaByDUI(String dui) {
        return personaRepository.findByDUI(dui).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public Persona getPersonaByEmail(String email) {
        return personaRepository.findByEmail(email).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Persona> getAllPersonasPagination(Pageable pageable) {
        return personaRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Persona savePersona(Persona persona) {
        if(persona.getId() != null)
            throw new PersonaAlreadyExistsException("Ya existe una persona con ese id");
        persona.setIngreso(LocalDate.now());
        EstadoUsuario estadoUsuario = new EstadoUsuario();
        estadoUsuario.setId(1L);
        persona.setEstadoUsuario(estadoUsuario);
        return personaRepository.save(persona);
    }

    @Override
    @Transactional
    public Persona updatePersona(Long id,Persona persona) {
        Persona personaDB = personaRepository.findById(id).orElseThrow();
        personaDB.setNombre(persona.getNombre());
        personaDB.setApellido(persona.getApellido());
        personaDB.setEmail(persona.getEmail());
        personaDB.setDUI(persona.getDUI());
        personaDB.setFechaNacimiento(persona.getFechaNacimiento());
        personaDB.setDireccion(persona.getDireccion());
        personaDB.setEstadoUsuario(persona.getEstadoUsuario());
        personaDB.setGenero(persona.getGenero());
        personaDB.setTelefono(persona.getTelefono());
        return personaRepository.save(personaDB);
    }

    @Override
    @Transactional
    public Persona updateMembresiaPersona(Long id) {
        Persona personaDB = personaRepository.findById(id).orElseThrow();
        personaDB.setExpiracionMembresia(LocalDate.now().plusYears(1));
        return personaRepository.save(personaDB);
    }
}
