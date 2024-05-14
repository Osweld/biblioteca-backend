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
@Table(name = "estado_usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstadoUsuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_usuario",nullable = false,unique = true)
    private Long id;

    @NotBlank
    @Size(min = 5, max = 50)
    @Column(name = "estado",nullable = false,unique = true, length = 50)
    private String estado;
}
