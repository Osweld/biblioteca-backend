package sv.edu.ues.bibliotecabackend.models.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class PagoDatosDTO {
    private BigDecimal diaAnterior;
    private BigDecimal ultimaSemana;
    private BigDecimal UltimoMes;

    public PagoDatosDTO(BigDecimal diaAnterior, BigDecimal ultimaSemana, BigDecimal ultimoMes) {
        this.diaAnterior = diaAnterior;
        this.ultimaSemana = ultimaSemana;
        UltimoMes = ultimoMes;
    }
}
