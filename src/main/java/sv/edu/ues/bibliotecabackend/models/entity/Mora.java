package sv.edu.ues.bibliotecabackend.models.entity;


import jakarta.persistence.*;
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
    @Column(name = "monto", nullable = false, precision = 10, scale = 2)
    private BigDecimal monto;
}
