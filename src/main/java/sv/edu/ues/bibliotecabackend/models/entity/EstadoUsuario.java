package sv.edu.ues.bibliotecabackend.models.entity;

import jakarta.persistence.*;
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

    @Column(name = "estado",nullable = false,unique = true, length = 50)
    private String estado;
}
