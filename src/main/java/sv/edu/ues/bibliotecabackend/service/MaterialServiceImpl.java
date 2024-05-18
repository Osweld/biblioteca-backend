package sv.edu.ues.bibliotecabackend.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.ues.bibliotecabackend.exceptions.MaterialAlreadyExistsException;
import sv.edu.ues.bibliotecabackend.models.entity.Inventario;
import sv.edu.ues.bibliotecabackend.models.entity.Material;
import sv.edu.ues.bibliotecabackend.models.especification.MaterialSpecification;
import sv.edu.ues.bibliotecabackend.models.repository.InventarioRepository;
import sv.edu.ues.bibliotecabackend.models.repository.MaterialRepository;

@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;
    private final MaterialSpecification materialSpecification;
    private final InventarioRepository inventarioRepository;

    public MaterialServiceImpl(MaterialRepository materialRepository, MaterialSpecification materialSpecification, InventarioRepository inventarioRepository) {
        this.materialRepository = materialRepository;
        this.materialSpecification = materialSpecification;
        this.inventarioRepository = inventarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Material getMaterialById(Long id) {
        return materialRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Material> getAllMaterials(Long idIdioma, Long idAutor, Long idCategoria, Long idEstadoMaterial, Pageable pageable) {
        Specification<Material> spec = materialSpecification.materialSpecification(idIdioma, idAutor, idCategoria, idEstadoMaterial);
        return materialRepository.findAll(spec,pageable);
    }

    @Override
    @Transactional()
    public Material saveMaterial(Material material) {
        if(material.getId() != null)
            throw new MaterialAlreadyExistsException("Material already exists");
        Material materialDB = materialRepository.save(material);
        Inventario inventario = new Inventario();
        inventario.setMaterial(materialDB);
        inventario.setStock(materialDB.getCantidad());
        inventarioRepository.save(inventario);
        return materialDB;
    }

    @Override
    @Transactional()
    public Material updateMaterial(Long id,Material material) {
        Material materialDB = materialRepository.findById(id).orElseThrow();
        materialDB.setIsbm(material.getIsbm());
        materialDB.setAutor(material.getAutor());
        materialDB.setEstadoMaterial(material.getEstadoMaterial());
        materialDB.setCategoria(material.getCategoria());
        materialDB.setIdioma(material.getIdioma());
        materialDB.setTitulo(material.getTitulo());
        materialDB.setDescripcion(material.getDescripcion());

        if(!materialDB.getCantidad().equals(material.getCantidad())){
            Integer diferencia = material.getCantidad() - materialDB.getCantidad();
            Inventario inventarioDB = inventarioRepository.findInventarioByMaterial(materialDB).orElseThrow();
            inventarioDB.setStock(inventarioDB.getStock() + diferencia);
            materialDB.setCantidad(material.getCantidad());
            inventarioRepository.save(inventarioDB);
        }
        return materialRepository.save(materialDB);
    }

}
