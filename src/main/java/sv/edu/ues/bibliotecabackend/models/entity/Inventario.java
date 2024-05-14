package sv.edu.ues.bibliotecabackend.models.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="inventarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventario implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario", nullable = false,unique = true)
    private Long id;

    @NotNull
    @Column(name = "stock", nullable = false)
    private Integer stock;

    @NotNull
    @OneToOne(optional = false)
    @JoinColumn(name = "id_material")
    private Material material;
}
