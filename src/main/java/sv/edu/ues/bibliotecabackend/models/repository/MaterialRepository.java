package sv.edu.ues.bibliotecabackend.models.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import sv.edu.ues.bibliotecabackend.models.entity.Material;

import java.util.Optional;

public interface MaterialRepository extends JpaRepository<Material, Long> ,JpaSpecificationExecutor<Material>, PagingAndSortingRepository<Material, Long> {


    Optional<Material> findById(Long id);


    Page<Material> findAll(Specification<Material> spec, Pageable pageable);
}
