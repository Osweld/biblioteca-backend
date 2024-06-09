package sv.edu.ues.bibliotecabackend.models.dto;

import lombok.Data;

@Data
public class EstadisticasDTO {

    private Integer prestamosActivos;
    private Integer totalMiembros;
    private Integer totalMaterialEducativoActivo;
    private Integer totalPrestamosRealizados;

}
