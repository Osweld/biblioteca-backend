package sv.edu.ues.bibliotecabackend.models.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PrestamoDiaDTO {
    private LocalDate dia;
    private Long numeroPrestamos;

    public PrestamoDiaDTO(LocalDate dia, Long numeroPrestamos) {
        this.dia = dia;
        this.numeroPrestamos = numeroPrestamos;
    }
}
