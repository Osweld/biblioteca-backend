package sv.edu.ues.bibliotecabackend.service;

import jakarta.mail.MessagingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.ues.bibliotecabackend.email.services.EmailService;
import sv.edu.ues.bibliotecabackend.models.dto.PasswordDTO;
import sv.edu.ues.bibliotecabackend.models.entity.EstadoUsuario;
import sv.edu.ues.bibliotecabackend.models.entity.Persona;
import sv.edu.ues.bibliotecabackend.models.entity.Rol;
import sv.edu.ues.bibliotecabackend.models.entity.Usuario;
import sv.edu.ues.bibliotecabackend.models.enums.EstadoUsuarioEnum;
import sv.edu.ues.bibliotecabackend.models.enums.RolEnum;
import sv.edu.ues.bibliotecabackend.models.repository.PersonaRepository;
import sv.edu.ues.bibliotecabackend.models.repository.UsuarioRepository;

import java.security.SecureRandom;
import java.time.LocalDate;


@Service
public class UsuarioServiceImpl implements UsuarioService{

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";
    private static final int PASSWORD_LENGTH = 12;

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final EmailService emailService;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PersonaRepository personaRepository, BCryptPasswordEncoder bCryptPasswordEncoder, EmailService emailService) {
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.emailService = emailService;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Usuario> findAll(Pageable pageable) {
        return usuarioRepository.findAllByRol(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return usuarioRepository.findByIdAndRol(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findByPersonaDUI(String dui) {
        return usuarioRepository.findByPersonaDUIAndRol(dui).orElseThrow();
    }


    @Override
    @Transactional
    public Usuario updateUsuario(Long id, Usuario usuario) {
        Usuario usuarioDB = usuarioRepository.findById(id).orElseThrow();
        Persona personaDB = usuarioDB.getPersona();
        personaDB.setNombre(usuario.getPersona().getNombre());
        personaDB.setApellido(usuario.getPersona().getApellido());
        personaDB.setEmail(usuario.getPersona().getEmail());
        personaDB.setDUI(usuario.getPersona().getDUI());
        personaDB.setFechaNacimiento(usuario.getPersona().getFechaNacimiento());
        personaDB.setDireccion(usuario.getPersona().getDireccion());
        personaDB.setGenero(usuario.getPersona().getGenero());
        personaDB.setTelefono(usuario.getPersona().getTelefono());
        usuarioDB.setPersona(personaRepository.save(personaDB));
        return usuarioRepository.save(usuarioDB);
    }

    @Override
    @Transactional
    public Usuario updatePassword(Long id, PasswordDTO passwordDTO) {
        Usuario usuarioDB = usuarioRepository.findById(id).orElseThrow();
        System.out.println("contraseña enviada: "+passwordDTO.getOldPassword());
        System.out.println("contraseña vieja: "+usuarioDB.getPassword());
        if(!bCryptPasswordEncoder.matches(passwordDTO.getOldPassword(),usuarioDB.getPassword())){
            throw new IllegalArgumentException("El password no coinciden");
        }else{
            usuarioDB.setPassword(bCryptPasswordEncoder.encode(passwordDTO.getNewPassword()));
        }
        return usuarioRepository.save(usuarioDB);
    }

    @Override
    public void resetPassword(String email) throws MessagingException {
        Usuario usuario = usuarioRepository.findUsuarioByEmail(email).orElseThrow();
        String newPassword = generateTemporaryPassword();
        usuario.setPassword(bCryptPasswordEncoder.encode(newPassword));
        Usuario usuarioDB = usuarioRepository.save(usuario);
        emailService.sendPassword(usuarioDB.getPersona().getEmail(),"Restablecimiento de contraseña Biblioteca Central de CentroAmerica",newPassword,usuarioDB);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario) {
        Persona persona = usuario.getPersona();
        persona.setIngreso(LocalDate.now());
        persona.setExpiracionMembresia(LocalDate.now().plusYears(10));
        EstadoUsuario estadoUsuario = new EstadoUsuario();
        estadoUsuario.setId(EstadoUsuarioEnum.ACTIVO.getId());
        persona.setEstadoUsuario(estadoUsuario);
        Rol rol = new Rol();
        rol.setId(RolEnum.BIBLIOTECARIO.getId());
        persona.setRol(rol);
        Persona personaDB = personaRepository.save(persona);
        if(RolEnum.fromId(personaDB.getRol().getId()) != RolEnum.BIBLIOTECARIO){
            throw new IllegalArgumentException("Los usuarios solo son validos para bibliotecarios");
        }
        usuario.setPersona(personaDB);
        usuario.setPassword(bCryptPasswordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }

    private static String generateTemporaryPassword() {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(PASSWORD_LENGTH);

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(index));
        }

        return password.toString();
    }
}
