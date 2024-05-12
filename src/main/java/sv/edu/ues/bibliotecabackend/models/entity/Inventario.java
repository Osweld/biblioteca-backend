package sv.edu.ues.bibliotecabackend.models.entity;


import jakarta.persistence.*;
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

    @Column(name = "stock", nullable = false)
    private Integer stock;

    @OneToOne
    @JoinColumn(name = "id_material")
    private Material material;
}
