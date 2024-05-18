package sv.edu.ues.bibliotecabackend.models.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @Size(min = 13,max = 13)
    @Column(name = "isbm",unique = true,length = 13)
    private String isbm;

    @NotBlank
    @Size(min = 5,max = 100)
    @Column(name = "titulo",nullable = false,length = 100)
    private String titulo;

    @NotNull
    @Column(name = "cantidad",nullable = false)
    private Integer cantidad;

    @NotNull
    @Size(min = 20, max = 500)
    @Column(name = "descripcion",nullable = false,length = 500)
    private String descripcion;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_idioma")
    private Idioma idioma;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_autor")
    private Autor autor;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_estado_material")
    private EstadoMaterial estadoMaterial;

    @OneToOne(mappedBy = "material", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Inventario inventario;

}
