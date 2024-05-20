package sv.edu.ues.bibliotecabackend.exceptions;

public class PersonaAlreadyExistsException extends RuntimeException{

    public PersonaAlreadyExistsException(String message) {
        super(message);
    }
}
