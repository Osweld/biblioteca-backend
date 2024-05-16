package sv.edu.ues.bibliotecabackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sv.edu.ues.bibliotecabackend.models.entity.Egreso;

public interface EgresoService {

    Page<Egreso> findAllEgreso(Pageable pageable);
    Egreso findEgresoById(Long id);
    Egreso saveEgreso(Egreso egreso);
    Egreso updateEgreso(Long id, Egreso egreso);
    Egreso deleteEgreso(Long id);
}
