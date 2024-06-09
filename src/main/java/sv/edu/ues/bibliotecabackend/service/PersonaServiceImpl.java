package sv.edu.ues.bibliotecabackend.service;

import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.ues.bibliotecabackend.email.services.EmailService;
import sv.edu.ues.bibliotecabackend.exceptions.PersonaAlreadyExistsException;
import sv.edu.ues.bibliotecabackend.models.entity.*;
import sv.edu.ues.bibliotecabackend.models.enums.EstadoUsuarioEnum;
import sv.edu.ues.bibliotecabackend.models.enums.RolEnum;
import sv.edu.ues.bibliotecabackend.models.enums.TipoPagoEnum;
import sv.edu.ues.bibliotecabackend.models.repository.CostoMiembroRepository;
import sv.edu.ues.bibliotecabackend.models.repository.PagoRepository;
import sv.edu.ues.bibliotecabackend.models.repository.PersonaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;
    private final PagoRepository  pagoRepository;
    private final CostoMiembroRepository costoMiembroRepository;
    private final EmailService emailService;

    public PersonaServiceImpl(PersonaRepository personaRepository, PagoRepository pagoRepository, CostoMiembroRepository costoMiembroRepository, EmailService emailService) {
        this.personaRepository = personaRepository;
        this.pagoRepository = pagoRepository;
        this.costoMiembroRepository = costoMiembroRepository;
        this.emailService = emailService;
    }

    @Override
    @Transactional(readOnly = true)
    public Persona getPersonaById(Long id) {
        return personaRepository.findByIdAndRoles(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public Persona getPersonaByDUI(String dui) {
        return personaRepository.findByDUIAndRoles(dui).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public Persona getPersonaByEmail(String email) {
        return personaRepository.findByEmailAndRoles(email).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Persona> getAllPersonasPagination(Pageable pageable) {
        return personaRepository.findAllByRoles(pageable);
    }

    @Override
    @Transactional
    public Persona savePersona(Persona persona) throws MessagingException {
        if(persona.getId() != null)
            throw new PersonaAlreadyExistsException("Ya existe una persona con ese id");
        Persona personaDB;
        if(RolEnum.fromId(persona.getRol().getId()) == RolEnum.MIEMBRO || RolEnum.fromId(persona.getRol().getId()) == RolEnum.PROFESOR){
            persona.setIngreso(LocalDate.now());
            persona.setExpiracionMembresia(LocalDate.now().plusYears(1));
            EstadoUsuario estadoUsuario = new EstadoUsuario();
            estadoUsuario.setId(EstadoUsuarioEnum.ACTIVO.getId());
            persona.setEstadoUsuario(estadoUsuario);
            personaDB = personaRepository.save(persona);

            Pago pagoIngreso = new Pago();

            pagoIngreso.setFechaPago(LocalDateTime.now());
            CostoMiembro costoMiembroIngreso = costoMiembroRepository.
                    findByTipoPagoId(TipoPagoEnum.INGRESO.getId()).orElseThrow();
            pagoIngreso.setMonto(costoMiembroIngreso.getMonto());
            pagoIngreso.setTipoPago(costoMiembroIngreso.getTipoPago());
            pagoIngreso.setPersona(personaDB);
            pagoRepository.save(pagoIngreso);
        }else{
            throw new IllegalArgumentException("El rol de la persona no es valido tiene que ser miembro o profesor");
        }


        emailService.createAccount(persona.getEmail(), "Bienvenido a la Biblioteca Central de CentroAmerica",personaDB);
        return personaDB;
    }

    @Override
    @Transactional
    public Persona updatePersona(Long id,Persona persona) {
        if(RolEnum.fromId(persona.getRol().getId()) == RolEnum.MIEMBRO || RolEnum.fromId(persona.getRol().getId()) == RolEnum.PROFESOR) {
            Persona personaDB = personaRepository.findById(id).orElseThrow();
            personaDB.setNombre(persona.getNombre());
            personaDB.setApellido(persona.getApellido());
            personaDB.setEmail(persona.getEmail());
            personaDB.setDUI(persona.getDUI());
            personaDB.setFechaNacimiento(persona.getFechaNacimiento());
            personaDB.setDireccion(persona.getDireccion());
            if(persona.getEstadoUsuario() != null)personaDB.setEstadoUsuario(persona.getEstadoUsuario());
            personaDB.setGenero(persona.getGenero());
            personaDB.setTelefono(persona.getTelefono());
            return personaRepository.save(personaDB);
        }else {
            throw new IllegalArgumentException("Solo se puede editar a miembros y profesores");
        }
    }

    @Override
    @Transactional
    public Persona updateMembresiaPersona(Long id) {
        Persona personaDB = personaRepository.findById(id).orElseThrow();
        personaDB.setExpiracionMembresia(LocalDate.now().plusYears(1));
        EstadoUsuario estadoUsuario = new EstadoUsuario();
        estadoUsuario.setId(EstadoUsuarioEnum.ACTIVO.getId());
        personaDB.setEstadoUsuario(estadoUsuario);
        return personaRepository.save(personaDB);
    }
}
