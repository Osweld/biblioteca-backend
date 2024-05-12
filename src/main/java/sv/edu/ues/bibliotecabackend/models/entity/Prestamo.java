package sv.edu.ues.bibliotecabackend.models.entity;

import jakarta.persistence.*;
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

    @Column(name = "fecha_prestamo",nullable = false)
    private LocalDateTime fechaPrestamo;

    @Column(name = "fecha_entrega")
    private LocalDateTime fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "id_miembro")
    private Usuario miembro;

    @ManyToOne
    @JoinColumn(name = "id_bibliotecario")
    private Usuario bibliotecario;

    @ManyToOne
    @JoinColumn(name = "id_material")
    private Material material;

    @ManyToOne
    @JoinColumn(name = "id_estado_prestamo")
    private EstadoPrestamo estadoPrestamo;
}
