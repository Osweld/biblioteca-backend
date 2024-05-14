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
@Table(name="moras")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Mora implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mora", nullable = false,unique = true)
    private Long id;

    @NotNull
    @Digits(integer = 10, fraction = 2)
    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;
}
