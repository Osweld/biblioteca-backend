package sv.edu.ues.bibliotecabackend.models.enums;

public enum RolEnum {

    MIEMBRO(1L),
    PROFESOR(2L),
    BIBLIOTECARIO(3L),
    ADMINISTRADOR(4L);

    private final long id;

    RolEnum(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public static RolEnum fromId(long id) {
        for (RolEnum rol : values()) {
            if (rol.getId() == id) {
                return rol;
            }
        }
        throw new IllegalArgumentException("Rol ID desconocido: " + id);
    }


}
