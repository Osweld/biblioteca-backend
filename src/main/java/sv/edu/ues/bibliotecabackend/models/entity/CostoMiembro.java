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

@Entity
@Table(name="costo_miembros")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CostoMiembro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_costo_miembro", nullable = false,unique = true)
    private Long id;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;

    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "id_tipo_pago",nullable = false)
    private TipoPago tipoPago;
}
