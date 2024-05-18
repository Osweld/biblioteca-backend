package sv.edu.ues.bibliotecabackend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.ues.bibliotecabackend.models.entity.Mora;
import sv.edu.ues.bibliotecabackend.models.repository.MoraRepository;

import java.math.BigDecimal;

@Service
public class MoraServiceImpl implements MoraService {

    private final MoraRepository moraRepository;

    public MoraServiceImpl(MoraRepository moraRepository) {
        this.moraRepository = moraRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Mora getMora() {
        return moraRepository.findById(1L).orElseThrow();
    }

    @Override
    @Transactional
    public Mora updateMora(Long id, BigDecimal monto) {
        Mora moraDB = moraRepository.findById(id).orElseThrow();
       moraDB.setMonto(monto);
        return moraRepository.save(moraDB);
    }
}
