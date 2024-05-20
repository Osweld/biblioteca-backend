package sv.edu.ues.bibliotecabackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.ues.bibliotecabackend.exceptions.EgresoAlreadyExistsException;
import sv.edu.ues.bibliotecabackend.models.entity.Egreso;
import sv.edu.ues.bibliotecabackend.models.repository.EgresoRepository;

@Service
public class EgresoServiceImpl implements EgresoService {

    private final EgresoRepository egresoRepository;

    public EgresoServiceImpl(EgresoRepository repository) {
        this.egresoRepository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Egreso> findAllEgreso(Pageable pageable) {
        return egresoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Egreso findEgresoById(Long id) {
        return egresoRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Egreso saveEgreso(Egreso egreso) {
        if(egreso.getId() != null) {
            throw new EgresoAlreadyExistsException("Este egreso ya existe en la base de datos");
        }
        return egresoRepository.save(egreso);
    }

    @Override
    @Transactional
    public Egreso updateEgreso(Long id, Egreso egreso) {
        Egreso egresoDB = egresoRepository.findById(id).orElseThrow();
        egresoDB.setTipoEgreso(egreso.getTipoEgreso());
        egresoDB.setMonto(egreso.getMonto());
        return egresoRepository.save(egresoDB);
    }

    @Override
    @Transactional
    public Egreso deleteEgreso(Long id) {
        Egreso egresoDB = egresoRepository.findById(id).orElseThrow();
        egresoRepository.delete(egresoDB);
        return egresoDB;
    }
}
