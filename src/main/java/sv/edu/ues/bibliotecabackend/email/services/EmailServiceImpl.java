package sv.edu.ues.bibliotecabackend.email.services;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import sv.edu.ues.bibliotecabackend.models.entity.Persona;
import sv.edu.ues.bibliotecabackend.models.entity.Prestamo;
import sv.edu.ues.bibliotecabackend.models.entity.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String email;

    private final JavaMailSender javaMailSender;


    public EmailServiceImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendPassword(String to, String subject, String password, Usuario usuario) throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        mimeMessage.setFrom(email);
        mimeMessage.setRecipients(Message.RecipientType.TO, to);
        mimeMessage.setSubject(subject);
        String htmlContent = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }" +
                ".container { background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); max-width: 600px; margin: 0 auto; }" +
                "h1 { color: #333333; }" +
                "p { color: #555555; }" +
                ".password { font-size: 1.2em; color: #d9534f; font-weight: bold; }" +
                ".footer { margin-top: 20px; font-size: 0.9em; color: #888888; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<h1>Biblioteca Central de Centroamérica</h1>" +
                "<p>Estimado "+usuario.getPersona().getNombre()+" "+ usuario.getPersona().getApellido()+",</p>" +
                "<p>Su contraseña ha sido cambiada con éxito. Por favor, utilice la siguiente contraseña temporal:</p>" +
                "<p class='password'>" + password + "</p>" +
                "<p>Recuerde actualizar su contraseña nuevamente desde la sección de configuración para garantizar la seguridad de su cuenta.</p>" +
                "<p>Gracias por su atención.</p>" +
                "<p class='footer'>Biblioteca Central de Centroamérica &copy; 2024</p>" +
                "</div>" +
                "</body>" +
                "</html>";

        mimeMessage.setContent(htmlContent, "text/html; charset=utf-8");
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void createAccount(String to, String subject, Persona persona) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        mimeMessage.setFrom(email);
        mimeMessage.setRecipients(Message.RecipientType.TO, to);
        mimeMessage.setSubject(subject);

        String htmlContent = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }" +
                ".container { background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); max-width: 600px; margin: 0 auto; }" +
                "h1 { color: #333333; }" +
                "p { color: #555555; }" +
                ".footer { margin-top: 20px; font-size: 0.9em; color: #888888; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<h1>Biblioteca Central de Centroamérica</h1>" +
                "<p>Estimado/a " + persona.getNombre() + " " + persona.getApellido() + ",</p>" +
                "<p>Nos complace darle la bienvenida como nuevo miembro de nuestra biblioteca. Su cuenta ha sido aprobada y ahora puede disfrutar de todos los recursos y servicios que ofrecemos.</p>" +
                "<p>Estamos encantados de tenerle con nosotros y esperamos que encuentre valiosos los materiales y servicios disponibles.</p>" +
                "<p>Si tiene alguna pregunta o necesita asistencia, no dude en contactarnos.</p>" +
                "<p>Gracias por unirse a nuestra comunidad.</p>" +
                "<p class='footer'>Biblioteca Central de Centroamérica &copy; 2024</p>" +
                "</div>" +
                "</body>" +
                "</html>";

        mimeMessage.setContent(htmlContent, "text/html; charset=utf-8");
        javaMailSender.send(mimeMessage);
    }


    @Override
    public void prestamoNotificacion(String to, String subject, Prestamo prestamo) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        mimeMessage.setFrom(email);
        mimeMessage.setRecipients(Message.RecipientType.TO, to);
        mimeMessage.setSubject(subject);

        LocalDate fechaEntrega = prestamo.getFechaEntrega().toLocalDate();
        String nombreCompleto = prestamo.getMiembro().getNombre() + " " + prestamo.getMiembro().getApellido();
        String material = prestamo.getMaterial().getTitulo();
        String autor = prestamo.getMaterial().getAutor().getAutor();

        String htmlContent = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "<meta charset='UTF-8'>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }" +
                ".container { background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 0 10px rgba(0,0,0,0.1); max-width: 600px; margin: 0 auto; }" +
                "h1 { color: #333333; }" +
                "p { color: #555555; }" +
                ".footer { margin-top: 20px; font-size: 0.9em; color: #888888; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<h1>Biblioteca Central de Centroamérica</h1>" +
                "<p>Estimado/a " + nombreCompleto + ",</p>" +
                "<p>Le recordamos que el préstamo del siguiente material educativo vence mañana:</p>" +
                "<p><strong>Título:</strong> " + material + "<br>" +
                "<strong>Autor:</strong> " + autor + "<br>" +
                "<strong>Fecha de entrega:</strong> " + fechaEntrega + "</p>" +
                "<p>Le agradecemos que devuelva el material a tiempo para evitar sanciones y permitir que otros miembros puedan acceder a él.</p>" +
                "<p>Gracias por su atención.</p>" +
                "<p class='footer'>Biblioteca Central de Centroamérica &copy; 2024</p>" +
                "</div>" +
                "</body>" +
                "</html>";

        mimeMessage.setContent(htmlContent, "text/html; charset=utf-8");
        javaMailSender.send(mimeMessage);
    }



}
