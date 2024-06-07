package sv.edu.ues.bibliotecabackend.models.enums;

public enum EstadoUsuarioEnum {
    CREADO(1L, "CREADO"),
    ACTIVO(2L, "ACTIVO"),
    DESACTIVADO(3L, "DESACTIVADO"),
    SANCIONADO(4L, "SANCIONADO"),
    RECHAZADO(5L, "RECHAZADO");

    private final Long id;
    private final String estado;

    EstadoUsuarioEnum(Long id, String estado) {
        this.id = id;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public String getEstado() {
        return estado;
    }

    public static EstadoUsuarioEnum fromId(Long id) {
        for (EstadoUsuarioEnum e : values()) {
            if (e.id.longValue() == id.longValue()) {
                return e;
            }
        }
        throw new IllegalArgumentException("Invalid EstadoUsuarioEnum id: " + id);
    }

    public static EstadoUsuarioEnum fromEstado(String estado) {
        for (EstadoUsuarioEnum e : values()) {
            if (e.estado.equalsIgnoreCase(estado)) {
                return e;
            }
        }
        throw new IllegalArgumentException("Invalid EstadoUsuarioEnum estado: " + estado);
    }
}
