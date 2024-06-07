package sv.edu.ues.bibliotecabackend.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.ues.bibliotecabackend.models.entity.CostoMiembro;
import sv.edu.ues.bibliotecabackend.models.entity.TipoPago;
import sv.edu.ues.bibliotecabackend.models.repository.CostoMiembroRepository;
import sv.edu.ues.bibliotecabackend.models.repository.TipoPagoRepository;

import java.math.BigDecimal;

@Service
public class CostoMiembroServiceImpl implements CostoMiembroService {

    private final CostoMiembroRepository costoMiembroRepository;
    private final TipoPagoRepository tipoPagoRepository;

    public CostoMiembroServiceImpl(CostoMiembroRepository costoMiembroRepository, TipoPagoRepository tipoPagoRepository) {
        this.costoMiembroRepository = costoMiembroRepository;
        this.tipoPagoRepository = tipoPagoRepository;
    }


    @Override
    public CostoMiembro getCostoMiembroByTipoPago(Long idTipoPago) {
        return costoMiembroRepository.findByTipoPagoId(idTipoPago).orElseThrow();
    }

    @Override
    public CostoMiembro updateCostoMiembroByTipopago(Long idTipoPago, CostoMiembro costoMiembro) {
        CostoMiembro costoMiembroDB = costoMiembroRepository.findByTipoPagoId(idTipoPago).orElseThrow();
        costoMiembroDB.setMonto(costoMiembro.getMonto());
        return costoMiembroRepository.save(costoMiembroDB);
    }
}
