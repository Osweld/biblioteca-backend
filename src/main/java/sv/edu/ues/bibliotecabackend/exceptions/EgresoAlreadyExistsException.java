package sv.edu.ues.bibliotecabackend.exceptions;

public class EgresoAlreadyExistsException extends RuntimeException {

    public EgresoAlreadyExistsException(String message) {
        super(message);
    }
}

