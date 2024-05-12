package sv.edu.ues.bibliotecabackend.models.entity;


import jakarta.persistence.*;
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
    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;
    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;
    @Column(name = "fecha_pago", nullable = false)
    private LocalDateTime fechaPago;

}
