package lt.gelumind.personallibrary.services;

import lombok.RequiredArgsConstructor;
import lt.gelumind.personallibrary.dao.AuthorRepository;
import lt.gelumind.personallibrary.exception.AuthorNotFoundException;
import lt.gelumind.personallibrary.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    @Autowired
    private final AuthorRepository authorRepository;

    private final List<Author> authors = new ArrayList<>();

    @Transactional
    public boolean addAuthor(Author author) {
        return authorRepository.save(author) != null ;
    }

    @Transactional
    public Optional<Author> getById(Long id) {
        return authorRepository.findById(id) ;
    }

    @Transactional
    public List<Author> getAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }

    @Transactional
    public List<Author> getByFirstName(String firstName) {
        List<Author> authors = new ArrayList<Author>() ;
        authors = authorRepository.findByFirstNameContaining(firstName) ;
        return authors;
    }

    @Transactional
    public List<Author> getByLastName(String lastName) {
        List<Author> authors = new ArrayList<Author>() ;
        authors = authorRepository.findByLastNameContaining(lastName) ;
        return authors;
    }

    @Transactional
    public boolean updateAuthor(Author author) {
        return authorRepository.save(author) != null;
    }

    @Transactional
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
