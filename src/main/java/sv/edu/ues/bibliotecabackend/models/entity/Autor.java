package sv.edu.ues.bibliotecabackend.models.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="autores")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Autor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autor",nullable = false,unique = true)
    private Long id;

    @Column(name = "autor", nullable = false,unique = true,length = 100)
    private String autor;

}
