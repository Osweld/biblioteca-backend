package sv.edu.ues.bibliotecabackend.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="tipo_egreso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoEgreso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_egreso", nullable = false,unique = true)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "tipo", nullable = false,unique = true, length = 50)
    private String tipo;

}
