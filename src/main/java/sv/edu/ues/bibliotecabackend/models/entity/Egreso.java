package sv.edu.ues.bibliotecabackend.models.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="egresos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Egreso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_egreso", nullable = false,unique = true)
    private Long id;


    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;


    @Column(name = "fecha_pago", nullable = false)
    private LocalDateTime fechaPago;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_tipo_egreso", nullable = false)
    private TipoEgreso tipoEgreso;

}
