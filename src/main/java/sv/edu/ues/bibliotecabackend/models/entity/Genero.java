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
@Table(name="generos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Genero implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_genero",nullable = false,unique = true)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 20)
    @Column(name = "nombre", nullable = false,unique = true, length = 20)
    private String nombre;
}
