package sv.edu.ues.bibliotecabackend.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="personas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Persona implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona", nullable = false,unique = true)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "nombre", nullable = false,length = 50)
    private String nombre;

    @NotBlank
    @Size(min = 2, max = 50)
    @Column(name = "apellido",nullable = false,length = 50)
    private String apellido;

    @NotBlank
    @Size(min = 5, max = 50)
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$")
    @Column(name = "email",nullable = false,unique = true, length = 50)
    private String email;

    @NotBlank
    @Size(min = 8, max = 8)
    @Column(name = "telefono",nullable = false, length = 8)
    private String telefono;

    @NotBlank
    @Pattern(regexp = "^\\d{8}-\\d{1}$")
    @Column(name = "dui",nullable = false,unique = true, length = 9)
    private String DUI;

    @NotBlank
    @Size(min = 10, max = 150)
    @Column(name = "direccion",nullable = false,length = 150)
    private String direccion;

    @NotNull
    @Column(name = "fecha_nacimiento",nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "ingreso", nullable = false)
    private LocalDate ingreso;

    @Column(name = "expiracion_menbresia",nullable = false)
    private LocalDate expiracionMembresia;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_roles", nullable = false)
    private Rol rol;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_genero",nullable = false)
    private Genero genero;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_estado_usuario",nullable = false)
    private EstadoUsuario estadoUsuario;
}
