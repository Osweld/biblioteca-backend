package sv.edu.ues.bibliotecabackend.models.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name="idiomas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Idioma implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_idioma", nullable = false,unique = true)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 50)
    @Column(name = "nombre", nullable = false,unique = true, length = 50)
    private String nombre;
}
