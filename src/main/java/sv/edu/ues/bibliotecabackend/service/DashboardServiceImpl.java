package sv.edu.ues.bibliotecabackend.service;

import org.springframework.stereotype.Service;
import sv.edu.ues.bibliotecabackend.models.dto.EgresoDatosDTO;
import sv.edu.ues.bibliotecabackend.models.dto.EstadisticasDTO;
import sv.edu.ues.bibliotecabackend.models.dto.PagoDatosDTO;
import sv.edu.ues.bibliotecabackend.models.dto.PrestamoDiaDTO;
import sv.edu.ues.bibliotecabackend.models.repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class DashboardServiceImpl implements DashboardService {

    private final PersonaRepository personaRepository;
    private final PrestamoRepository prestamoRepository;
    private final MaterialRepository materialRepository;
    private final PagoRepository pagoRepository;
    private final EgresoRepository egresoRepository;

    public DashboardServiceImpl(PersonaRepository personaRepository, PrestamoRepository prestamoRepository, MaterialRepository materialRepository, PagoRepository pagoRepository, EgresoRepository egresoRepository) {
        this.personaRepository = personaRepository;
        this.prestamoRepository = prestamoRepository;
        this.materialRepository = materialRepository;
        this.pagoRepository = pagoRepository;
        this.egresoRepository = egresoRepository;
    }

    @Override
    public EstadisticasDTO getBasicStatistics() {
        EstadisticasDTO estadisticasDTO = new EstadisticasDTO();
        estadisticasDTO.setTotalMiembros(personaRepository.countByRoles());
        estadisticasDTO.setPrestamosActivos(prestamoRepository.countPrestamosByEstadosActivos());
        estadisticasDTO.setTotalPrestamosRealizados(prestamoRepository.countPrestamosByEstadosRealizados());
        estadisticasDTO.setTotalMaterialEducativoActivo(materialRepository.countMaterialesByEstados());


        return estadisticasDTO;
    }

    @Override
    public List<PrestamoDiaDTO> getPrestamosPorDiaUltimaSemana() {
        LocalDateTime oneWeekAgo = LocalDateTime.now().minusWeeks(1);
        return prestamoRepository.countPrestamosByDay(oneWeekAgo);
    }

    @Override
    public PagoDatosDTO getPagoDatos() {
        LocalDateTime now = LocalDateTime.now();

        // Día anterior
        LocalDateTime startOfDayYesterday = now.minusDays(1).truncatedTo(ChronoUnit.DAYS);
        LocalDateTime endOfDayYesterday = startOfDayYesterday.plusDays(1).minusNanos(1);
        BigDecimal diaAnterior = Optional.ofNullable(pagoRepository.sumMontoBetweenDates(startOfDayYesterday, endOfDayYesterday))
                .orElse(BigDecimal.ZERO);

        // Última semana
        LocalDateTime startOfWeek = now.minusWeeks(1);
        BigDecimal ultimaSemana = Optional.ofNullable(pagoRepository.sumMontoBetweenDates(startOfWeek, now))
                .orElse(BigDecimal.ZERO);

        // Último mes
        LocalDateTime startOfMonth = now.minusMonths(1);
        BigDecimal ultimoMes = Optional.ofNullable(pagoRepository.sumMontoBetweenDates(startOfMonth, now))
                .orElse(BigDecimal.ZERO);

        return new PagoDatosDTO(diaAnterior, ultimaSemana, ultimoMes);
    }

    @Override
    public EgresoDatosDTO getEgresoDatos() {
        LocalDateTime now = LocalDateTime.now();

        // Día anterior
        LocalDateTime startOfDayYesterday = now.minusDays(1).truncatedTo(ChronoUnit.DAYS);
        LocalDateTime endOfDayYesterday = startOfDayYesterday.plusDays(1).minusNanos(1);
        BigDecimal diaAnterior = Optional.ofNullable(egresoRepository.sumMontoBetweenDates(startOfDayYesterday, endOfDayYesterday))
                .orElse(BigDecimal.ZERO);

        // Última semana
        LocalDateTime startOfWeek = now.minusWeeks(1);
        BigDecimal ultimaSemana = Optional.ofNullable(egresoRepository.sumMontoBetweenDates(startOfWeek, now))
                .orElse(BigDecimal.ZERO);

        // Último mes
        LocalDateTime startOfMonth = now.minusMonths(1);
        BigDecimal ultimoMes = Optional.ofNullable(egresoRepository.sumMontoBetweenDates(startOfMonth, now))
                .orElse(BigDecimal.ZERO);

        return new EgresoDatosDTO(diaAnterior, ultimaSemana, ultimoMes);
    }
}
