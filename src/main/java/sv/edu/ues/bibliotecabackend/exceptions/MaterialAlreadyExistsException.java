package sv.edu.ues.bibliotecabackend.exceptions;

public class MaterialAlreadyExistsException extends RuntimeException {

    public MaterialAlreadyExistsException(String message) {
        super(message);
    }
}
