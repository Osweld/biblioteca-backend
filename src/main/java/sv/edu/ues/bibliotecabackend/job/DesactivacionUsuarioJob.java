package sv.edu.ues.bibliotecabackend.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import sv.edu.ues.bibliotecabackend.models.entity.EstadoUsuario;
import sv.edu.ues.bibliotecabackend.models.entity.Persona;
import sv.edu.ues.bibliotecabackend.models.enums.RolEnum;
import sv.edu.ues.bibliotecabackend.models.repository.PersonaRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DesactivacionUsuarioJob implements Job {

    private final PersonaRepository personaRepository;

    public DesactivacionUsuarioJob(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        List<Persona> actualizarPersonas = new ArrayList<>();
        List<Persona> personas = personaRepository.findByExpiracionMembresiaYesterday(yesterday);

        // Aquí puedes realizar la lógica de desactivación o cualquier otra operación necesaria con las personas encontradas
        for (Persona persona : personas) {
            if(RolEnum.BIBLIOTECARIO.getId() == persona.getRol().getId()) {
                persona.setExpiracionMembresia(LocalDate.now().plusYears(10));
                actualizarPersonas.add(persona);
            }else{
                EstadoUsuario estadoUsuario = new EstadoUsuario();
                estadoUsuario.setId(3L);
                persona.setEstadoUsuario(estadoUsuario);
                actualizarPersonas.add(persona);
            }
        }

        personaRepository.saveAll(actualizarPersonas);

    }
}
