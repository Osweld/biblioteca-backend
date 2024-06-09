package sv.edu.ues.bibliotecabackend.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import sv.edu.ues.bibliotecabackend.models.entity.Persona;
import sv.edu.ues.bibliotecabackend.models.entity.Prestamo;
import sv.edu.ues.bibliotecabackend.models.entity.Usuario;
import sv.edu.ues.bibliotecabackend.models.repository.PersonaRepository;
import sv.edu.ues.bibliotecabackend.models.repository.PrestamoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class UsuarioSancionadoJob implements Job {

    private final PrestamoRepository prestamoRepository;
    private final PersonaRepository personaRepository;

    public UsuarioSancionadoJob(PrestamoRepository prestamoRepository, PersonaRepository personaRepository) {
        this.prestamoRepository = prestamoRepository;
        this.personaRepository = personaRepository;
    }


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        List<Persona> miembrosSancionados = new ArrayList<>();
        LocalDate fechaUnaSemanaYUnDiaAtras = LocalDate.now().minusDays(8);
        LocalDateTime fechaDeVencimientoAyerInicio = fechaUnaSemanaYUnDiaAtras.atStartOfDay();
        LocalDateTime fechaDeVencimientoAyerFin = fechaUnaSemanaYUnDiaAtras.atTime(LocalTime.MAX);
        List<Prestamo> prestamos = prestamoRepository.findPrestamosByFechaEntregaBetweenRetraso(fechaDeVencimientoAyerInicio,fechaDeVencimientoAyerFin);

        for (Prestamo prestamo : prestamos) {
            Persona miembro = prestamo.getMiembro();
            miembro.getEstadoUsuario().setId(4L);
            miembrosSancionados.add(miembro);
        }

        personaRepository.saveAll(miembrosSancionados);
    }
}
