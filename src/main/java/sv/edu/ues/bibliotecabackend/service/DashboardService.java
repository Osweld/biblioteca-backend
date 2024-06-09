package sv.edu.ues.bibliotecabackend.service;

import sv.edu.ues.bibliotecabackend.models.dto.EgresoDatosDTO;
import sv.edu.ues.bibliotecabackend.models.dto.EstadisticasDTO;
import sv.edu.ues.bibliotecabackend.models.dto.PagoDatosDTO;
import sv.edu.ues.bibliotecabackend.models.dto.PrestamoDiaDTO;

import java.util.List;

public interface DashboardService {

    EstadisticasDTO getBasicStatistics();
    List<PrestamoDiaDTO> getPrestamosPorDiaUltimaSemana();
    PagoDatosDTO getPagoDatos();
    EgresoDatosDTO getEgresoDatos();
}
