package sv.edu.ues.bibliotecabackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sv.edu.ues.bibliotecabackend.models.entity.Material;

public interface MaterialService {

    Material getMaterialById(Long id);
    Page<Material> getAllMaterials(Long idIdioma,Long idAutor, Long idCategoria, Long idEstadoMaterial,Pageable pageable);
    Material saveMaterial(Material material);
    Material updateMaterial(Long id,Material material);

}
