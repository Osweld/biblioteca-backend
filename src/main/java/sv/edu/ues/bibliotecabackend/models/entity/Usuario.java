package sv.edu.ues.bibliotecabackend.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false,unique = true)
    private Long id;

    @Column(name = "nombre", nullable = false,length = 50)
    private String nombre;

    @Column(name = "apellido",nullable = false,length = 50)
    private String apellido;

    @Column(name = "email",nullable = false,unique = true, length = 50)
    private String email;

    @Column(name = "telefono",nullable = false, length = 8)
    private String telefono;

    @Column(name = "dui",nullable = false,unique = true, length = 9)
    private String DUI;

    @Column(name = "direccion",nullable = false,length = 150)
    private String direccion;

    @Column(name = "fecha_nacimiento",nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "ingreso", nullable = false)
    private LocalDate ingreso;

    @Column(name = "usuario",nullable = false,unique = true, length = 20)
    private String usuario;

    @Column(name = "password",nullable = false,length = 60)
    private String password;

    @Column(name = "expiracion_menbresia",nullable = false)
    private LocalDate expiracionMembresia;

    @ManyToOne
    @JoinColumn(name = "id_roles", nullable = false)
    private Rol rol;
    @ManyToOne
    @JoinColumn(name = "id_genero",nullable = false)
    private Genero genero;
    @ManyToOne
    @JoinColumn(name = "id_estado_usuario",nullable = false)
    private EstadoUsuario estadoUsuario;
}
