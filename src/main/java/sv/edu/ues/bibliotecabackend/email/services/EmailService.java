package sv.edu.ues.bibliotecabackend.email.services;

import jakarta.mail.MessagingException;
import sv.edu.ues.bibliotecabackend.models.entity.Persona;
import sv.edu.ues.bibliotecabackend.models.entity.Prestamo;
import sv.edu.ues.bibliotecabackend.models.entity.Usuario;

public interface EmailService {

    public void sendPassword(String to, String subject, String password, Usuario usuario) throws MessagingException;
    public void createAccount(String to, String subject, Persona persona) throws MessagingException;
    public void prestamoNotificacion(String to, String subject, Prestamo prestamo) throws MessagingException;
}
