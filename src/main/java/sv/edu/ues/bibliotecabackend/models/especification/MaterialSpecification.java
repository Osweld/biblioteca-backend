package sv.edu.ues.bibliotecabackend.models.especification;


import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import sv.edu.ues.bibliotecabackend.models.entity.Material;

import java.util.ArrayList;
import java.util.List;

@Component
public class MaterialSpecification {

    public Specification<Material> materialSpecification(Long idIdioma, Long idAutor, Long idCategoria, Long idEstadoMaterial) {
        return (root, query, builder) -> {
            // Asegúrate de que las asociaciones se obtienen correctamente
            //root.fetch("inventario", JoinType.LEFT);


            query.distinct(true);

            List<Predicate> predicates = new ArrayList<>();

            if (idIdioma != null) {
                predicates.add(builder.equal(root.get("idioma").get("id"), idIdioma));
            }

            if (idAutor != null) {
                predicates.add(builder.equal(root.get("autor").get("id"), idAutor));
            }

            if (idCategoria != null) {
                predicates.add(builder.equal(root.get("categoria").get("id"), idCategoria));
            }

            if (idEstadoMaterial != null) {
                predicates.add(builder.equal(root.get("estadoMaterial").get("id"), idEstadoMaterial));
            }

            // Agrega la condición a la consulta
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}