package sv.edu.ues.bibliotecabackend.models.projection;

import java.time.LocalDate;

/**
 * Projection for {@link sv.edu.ues.bibliotecabackend.models.entity.Usuario}
 */
public interface UsuarioWithoutPassword {
    Long getId();

    String getUsername();

    Persona getPersona();

    /**
     * Projection for {@link sv.edu.ues.bibliotecabackend.models.entity.Persona}
     */
    interface Persona {
        Long getId();

        String getNombre();

        String getApellido();

        String getEmail();

        String getTelefono();

        String getDUI();

        String getDireccion();

        LocalDate getFechaNacimiento();

        LocalDate getIngreso();

        LocalDate getExpiracionMembresia();

        RolInfo getRol();

        GeneroInfo getGenero();

        EstadoUsuarioInfo getEstadoUsuario();

        /**
         * Projection for {@link sv.edu.ues.bibliotecabackend.models.entity.Rol}
         */
        interface RolInfo {
            Long getId();

            String getRol();
        }

        /**
         * Projection for {@link sv.edu.ues.bibliotecabackend.models.entity.Genero}
         */
        interface GeneroInfo {
            Long getId();

            String getNombre();
        }

        /**
         * Projection for {@link sv.edu.ues.bibliotecabackend.models.entity.EstadoUsuario}
         */
        interface EstadoUsuarioInfo {
            Long getId();

            String getEstado();
        }
    }
}