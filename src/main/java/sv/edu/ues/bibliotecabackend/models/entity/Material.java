package sv.edu.ues.bibliotecabackend.models.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name="materiales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Material implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material", nullable = false,unique = true)
    private Long id;

    @Column(name = "isbm", nullable = false,unique = true,length = 13)
    private String isbm;

    @Column(name = "titulo",nullable = false,length = 100)
    private String titulo;

    @Column(name = "cantidad",nullable = false)
    private Integer cantidad;

    @Column(name = "descripcion",nullable = false,length = 500)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_idioma")
    private Idioma idioma;
    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;
    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "id_estado_material")
    private EstadoMaterial estadoMaterial;

}
