package sv.edu.ues.bibliotecabackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import sv.edu.ues.bibliotecabackend.models.entity.Autor;

import java.util.List;

public interface AutorService {

    Autor getAutor(Long id);
    Page<Autor> getAutorsByPagination(Pageable pageable);
    List<Autor> getAutorByText(String nombre);
    Autor saveAutor(Autor autor);
    Autor updateAutor(Long id, Autor autor);
    Autor deleteAutor(Long id);
}
