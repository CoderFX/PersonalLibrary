package lt.gelumind.personallibrary.dao;

import lt.gelumind.personallibrary.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findAll();
    Optional<Author> findById(Long id);
    List<Author> findByFirstNameContaining(String firstName);
    List<Author> findByLastNameContaining(String lastName);

}
