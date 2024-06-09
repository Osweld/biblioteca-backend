package sv.edu.ues.bibliotecabackend.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;
import sv.edu.ues.bibliotecabackend.models.entity.Persona;
import sv.edu.ues.bibliotecabackend.models.entity.Prestamo;
import sv.edu.ues.bibliotecabackend.models.repository.PrestamoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class PrestamoMorosoJob implements Job {

    private final PrestamoRepository prestamoRepository;

    public PrestamoMorosoJob(PrestamoRepository prestamoRepository) {
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("PrestamoMorosoJob");
        List<Prestamo> prestamosMorosos = new ArrayList<>();

        LocalDate fechaDeVencimientoAyer = LocalDate.now().minusDays(1);
        LocalDateTime fechaDeVencimientoAyerInicio = fechaDeVencimientoAyer.atStartOfDay();
        LocalDateTime fechaDeVencimientoAyerFin = fechaDeVencimientoAyer.atTime(LocalTime.MAX);
        List<Prestamo> prestamos = prestamoRepository.findPrestamosByFechaEntregaBetween(fechaDeVencimientoAyerInicio,fechaDeVencimientoAyerFin);
        for (Prestamo prestamo : prestamos) {
            prestamo.getEstadoPrestamo().setId(3L);
            prestamosMorosos.add(prestamo);
        }

        prestamoRepository.saveAll(prestamosMorosos);
    }
}
