package sv.edu.ues.bibliotecabackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sv.edu.ues.bibliotecabackend.exceptions.AutorAlreadyExistsException;
import sv.edu.ues.bibliotecabackend.models.entity.Autor;
import sv.edu.ues.bibliotecabackend.models.repository.AutorRepository;

import java.util.List;

@Service
public class AutorServiceImpl implements AutorService{

    private final AutorRepository autorRepository;

    public AutorServiceImpl(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public Autor getAutor(Long id) {
        return autorRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Autor> getAutorsByPagination(Pageable pageable) {
        return autorRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Autor> getAutorByText(String nombre) {
        return autorRepository.findByAutorContainingIgnoreCase(nombre);
    }

    @Override
    @Transactional
    public Autor saveAutor(Autor autor) {
        if(autor.getId() != null){
            throw new AutorAlreadyExistsException("El autor ya existe");
        }
        return autorRepository.save(autor);
    }

    @Override
    @Transactional
    public Autor updateAutor(Long id, Autor autor) {
        Autor autorDB = autorRepository.findById(id).orElseThrow();
        autorDB.setAutor(autor.getAutor());
        return autorRepository.save(autorDB);
    }

    @Override
    @Transactional
    public Autor deleteAutor(Long id) {
        Autor autor = autorRepository.findById(id).orElseThrow();
        autorRepository.delete(autor);
        return autor;
    }
}
