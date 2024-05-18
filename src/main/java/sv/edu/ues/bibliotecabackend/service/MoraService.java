package sv.edu.ues.bibliotecabackend.service;

import sv.edu.ues.bibliotecabackend.models.entity.Mora;

import java.math.BigDecimal;

public interface MoraService {

    Mora getMora();
    Mora updateMora(Long id, BigDecimal monto);
}
