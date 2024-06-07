package sv.edu.ues.bibliotecabackend.models.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="pagos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pago implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago", nullable = false,unique = true)
    private Long id;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @NotNull
    @Column(name = "fecha_pago", nullable = false)
    private LocalDateTime fechaPago;


    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_persona", nullable = false)
    private Persona persona;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_tipo_pago", nullable = false)
    private TipoPago tipoPago;
}
