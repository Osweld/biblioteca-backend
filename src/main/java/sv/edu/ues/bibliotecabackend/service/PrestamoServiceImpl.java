package sv.edu.ues.bibliotecabackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sv.edu.ues.bibliotecabackend.models.entity.*;
import sv.edu.ues.bibliotecabackend.models.enums.RolEnum;
import sv.edu.ues.bibliotecabackend.models.enums.TipoPagoEnum;
import sv.edu.ues.bibliotecabackend.models.repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    private final PrestamoRepository prestamoRepository;
    private final CostoMiembroRepository costoMiembroRepository;
    private final PagoRepository pagoRepository;
    private final InventarioRepository inventarioRepository;
    private final MaterialRepository materialRepository;
    private final PersonaRepository personaRepository;

    public PrestamoServiceImpl(PrestamoRepository prestamoRepository, CostoMiembroRepository costoMiembroRepository, PagoRepository pagoRepository, InventarioRepository inventarioRepository, MaterialRepository materialRepository, PersonaRepository personaRepository) {
        this.prestamoRepository = prestamoRepository;
        this.costoMiembroRepository = costoMiembroRepository;
        this.pagoRepository = pagoRepository;
        this.inventarioRepository = inventarioRepository;
        this.materialRepository = materialRepository;
        this.personaRepository = personaRepository;
    }


    @Override
    public Page<Prestamo> findAll(Pageable pageable) {
        return prestamoRepository.findAll(pageable);
    }

    @Override
    public Prestamo getPrestamo(Long id) {
        return prestamoRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Prestamo> activePrestamoByPersonaId(Long id) {
        return prestamoRepository.findActiveByPersonaId(id);
    }

    @Override
    public Page<Prestamo> historialPrestamoBypersonaId(Long id, Pageable pageable) {
        return prestamoRepository.findHistorialByPersonaId(id, pageable);
    }

    @Override
    public Prestamo savePrestamo(Prestamo prestamo) {
        if (prestamo.getId() == null) {
            if (RolEnum.fromId(prestamo.getMiembro().getRol().getId()) == RolEnum.MIEMBRO || RolEnum.fromId(prestamo.getMiembro().getRol().getId()) == RolEnum.PROFESOR) {

                Persona persona = personaRepository.findById(prestamo.getMiembro().getId()).orElseThrow();

                if(persona.getEstadoUsuario().getId() != 2L){
                    throw new IllegalArgumentException("Solo se puede prestar a usuarios activos");
                }


                Material material = materialRepository.findById(prestamo.getMaterial().getId()).orElseThrow();

                if(material.getEstadoMaterial().getId() == 2L){
                    throw new IllegalArgumentException("No se puede prestar un material que esta desactivado");
                }
                Integer prestamosActuales = prestamoRepository.prestamosActualesByPersona(prestamo.getMiembro().getId());

                if (prestamosActuales >= 5) {
                    throw new IllegalArgumentException("El maximo para prestamo de material es 5");
                }

                Inventario inventario = inventarioRepository.findInventarioByMaterial(prestamo.getMaterial()).orElseThrow();

                if(inventario.getStock() < 1){
                    throw new IllegalArgumentException("No hay stock de ese material educativo");
                }

                inventario.setStock(inventario.getStock() - 1);
                EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
                estadoPrestamo.setId(1L);
                prestamo.setEstadoPrestamo(estadoPrestamo);
                prestamo.setFechaInicio(LocalDateTime.now());
                if (prestamo.getMiembro().getRol().getId() == RolEnum.MIEMBRO.getId()) {
                    prestamo.setFechaEntrega(LocalDateTime.now().plusDays(5));
                } else {
                    prestamo.setFechaEntrega(LocalDateTime.now().plusMonths(3));
                }

                inventarioRepository.save(inventario);
            } else {
                throw new IllegalArgumentException("Solo se puede editar a miembros y profesores");
            }
        }
        return prestamoRepository.save(prestamo);
    }

    @Override
    public Prestamo devolucion(Prestamo prestamo) {

        Prestamo prestamoDB = prestamoRepository.findById(prestamo.getId()).orElseThrow();

        if(prestamoDB.getEstadoPrestamo().getId() == 2L){
            throw new IllegalArgumentException("No se puede hacer la devolucion, si ya fue devuelto");
        }

        if (prestamoDB.getEstadoPrestamo().getId() == 3L) {
            Pago pagoMora = new Pago();

            pagoMora.setFechaPago(LocalDateTime.now());
            CostoMiembro costoMiembroMora = costoMiembroRepository.
                    findByTipoPagoId(TipoPagoEnum.MORA.getId()).orElseThrow();


            long tiempoDePrestamo = ChronoUnit.DAYS.between(prestamoDB.getFechaEntrega().toLocalDate(),LocalDateTime.now().toLocalDate());

            if(tiempoDePrestamo > 7){
                tiempoDePrestamo = 7;
            }

            BigDecimal montoTotal = costoMiembroMora.getMonto().multiply(new BigDecimal(tiempoDePrestamo));

            pagoMora.setMonto(montoTotal);
            pagoMora.setTipoPago(costoMiembroMora.getTipoPago());
            pagoMora.setPersona(prestamoDB.getMiembro());
            pagoRepository.save(pagoMora);
        }


        EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
        estadoPrestamo.setId(2L);
        prestamoDB.setEstadoPrestamo(estadoPrestamo);
        Inventario inventario = inventarioRepository.findInventarioByMaterial(prestamo.getMaterial()).orElseThrow();
        inventario.setStock(inventario.getStock() + 1);
        inventarioRepository.save(inventario);
        return prestamoRepository.save(prestamoDB);
    }

    @Override
    public Prestamo devolucionSinMaterial(Prestamo prestamo) {
        Prestamo prestamoDB = prestamoRepository.findById(prestamo.getId()).orElseThrow();
        if(prestamoDB.getEstadoPrestamo().getId() == 2L){
            throw new IllegalArgumentException("No se puede hacer la devolucion, si ya fue devuelto");
        }
        if (prestamoDB.getEstadoPrestamo().getId() == 3L || prestamoDB.getEstadoPrestamo().getId() == 1L) {
            Pago pagoPerdida = new Pago();
            pagoPerdida.setFechaPago(LocalDateTime.now());
            CostoMiembro costoMiembroPerdida = costoMiembroRepository.
                    findByTipoPagoId(TipoPagoEnum.PERDIDA.getId()).orElseThrow();


            pagoPerdida.setMonto(costoMiembroPerdida.getMonto());
            pagoPerdida.setTipoPago(costoMiembroPerdida.getTipoPago());
            pagoPerdida.setPersona(prestamoDB.getMiembro());
            pagoRepository.save(pagoPerdida);
        }


        EstadoPrestamo estadoPrestamo = new EstadoPrestamo();
        estadoPrestamo.setId(4L);
        prestamoDB.setEstadoPrestamo(estadoPrestamo);
        Inventario inventario = inventarioRepository.findInventarioByMaterial(prestamo.getMaterial()).orElseThrow();
        inventario.setStock(inventario.getStock());
        inventarioRepository.save(inventario);
        Material material = materialRepository.findById(prestamo.getMaterial().getId()).orElseThrow();
        material.setCantidad(material.getCantidad() - 1);
        materialRepository.save(material);
        return prestamoRepository.save(prestamoDB);
    }

    @Override
    public Prestamo renovarPrestamo(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id).orElseThrow();
        if ( RolEnum.fromId(prestamo.getMiembro().getRol().getId()) == RolEnum.PROFESOR) {
            if(prestamo.getEstadoPrestamo().getId() == 1){
                prestamo.setFechaEntrega(LocalDateTime.now().plusMonths(3));
            }else{
                throw new IllegalArgumentException("El prestamo no esta activo o esta en moroso");
            }

        }else {
            throw new IllegalArgumentException("Solo a los profesores se les puede renovar ");
        }
        return prestamoRepository.save(prestamo);
    }

    @Override
    public BigDecimal moraGenerada(Long id) {
        Prestamo prestamo = prestamoRepository.findById(id).orElseThrow();
            if (prestamo.getEstadoPrestamo().getId() == 3L) {
                Pago pagoMora = new Pago();
                pagoMora.setFechaPago(LocalDateTime.now());
                CostoMiembro costoMiembroMora = costoMiembroRepository.
                        findByTipoPagoId(TipoPagoEnum.MORA.getId()).orElseThrow();
                long tiempoDePrestamo = ChronoUnit.DAYS.between(prestamo.getFechaEntrega().toLocalDate(), LocalDateTime.now().toLocalDate());

                if (tiempoDePrestamo > 7) {
                    tiempoDePrestamo = 7;
                }

                return costoMiembroMora.getMonto().multiply(new BigDecimal(tiempoDePrestamo));
            }

        return BigDecimal.ZERO;
    }

    @Override
    public BigDecimal montoPorPerdida(Long idPersona) {
        CostoMiembro costoMiembroMora = costoMiembroRepository.
                findByTipoPagoId(TipoPagoEnum.PERDIDA.getId()).orElseThrow();
        return costoMiembroMora.getMonto();
    }


}
