package sv.edu.ues.bibliotecabackend.exceptions;

public class AutorAlreadyExistsException extends RuntimeException {

    public AutorAlreadyExistsException(String message) {
        super(message);
    }
}
