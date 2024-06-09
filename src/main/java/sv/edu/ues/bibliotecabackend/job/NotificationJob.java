package sv.edu.ues.bibliotecabackend.job;

import jakarta.mail.MessagingException;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sv.edu.ues.bibliotecabackend.email.services.EmailService;
import sv.edu.ues.bibliotecabackend.models.entity.Prestamo;
import sv.edu.ues.bibliotecabackend.models.repository.PrestamoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class NotificationJob implements Job {

    private final EmailService emailService;
    private final PrestamoRepository prestamoRepository;


    public NotificationJob(EmailService emailService, PrestamoRepository prestamoRepository) {
        this.emailService = emailService;
        this.prestamoRepository = prestamoRepository;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LocalDate fechaMañana = LocalDate.now().plusDays(1);
        LocalDateTime fechaDeVencimientoAyerInicio = fechaMañana.atStartOfDay();
        LocalDateTime fechaDeVencimientoAyerFin = fechaMañana.atTime(LocalTime.MAX);
        List<Prestamo> prestamos = prestamoRepository.findPrestamosByFechaEntregaBetween(fechaDeVencimientoAyerInicio,fechaDeVencimientoAyerFin);

        for (Prestamo prestamo : prestamos) {
            try {
                emailService.prestamoNotificacion(prestamo.getMiembro().getEmail(), "Préstamo vence pronto Biblioteca Central de Centroamérica", prestamo);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
