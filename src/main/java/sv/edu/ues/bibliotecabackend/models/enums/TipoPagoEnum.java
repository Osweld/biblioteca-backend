package sv.edu.ues.bibliotecabackend.models.enums;

public enum TipoPagoEnum {
    INGRESO(1L),
    MORA(2L),
    CALIDAD(3L),
    PERDIDA(4L);


    private long id;

    TipoPagoEnum(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public static TipoPagoEnum fromId(long id) {
        for (TipoPagoEnum tipoPago : values()) {
            if (tipoPago.getId() == id) {
                return tipoPago;
            }
        }
        throw new IllegalArgumentException("TipoPago ID desconocido: " + id);
    }
}
