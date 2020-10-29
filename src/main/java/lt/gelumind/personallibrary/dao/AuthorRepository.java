package lt.gelumind.personallibrary.dao;

import lt.gelumind.personallibrary.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
