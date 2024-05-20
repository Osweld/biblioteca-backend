package sv.edu.ues.bibliotecabackend.service;

import sv.edu.ues.bibliotecabackend.models.entity.CostoMiembro;

import java.math.BigDecimal;

public interface CostoMiembroService {


    CostoMiembro getCostoMiembroByTipoPago(Long idTipoPago);
    CostoMiembro updateCostoMiembroByTipopago(Long idTipoPago,CostoMiembro costoMiembro);
}
