package lt.gelumind.personallibrary.dao;

import lt.gelumind.personallibrary.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}