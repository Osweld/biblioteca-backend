package sv.edu.ues.bibliotecabackend.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="prestamos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Prestamo  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_prestamo", nullable = false,unique = true)
    private Long id;

    @NotNull
    @Column(name = "fecha_prestamo",nullable = false)
    private LocalDateTime fechaPrestamo;

    @Column(name = "fecha_entrega")
    private LocalDateTime fechaEntrega;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_miembro")
    private Persona miembro;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_bibliotecario")
    private Persona bibliotecario;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_material")
    private Material material;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_estado_prestamo")
    private EstadoPrestamo estadoPrestamo;
}
