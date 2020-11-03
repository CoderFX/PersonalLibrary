package lt.gelumind.personallibrary.dao;

import lt.gelumind.personallibrary.model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    List<Book> findAll();
    List<Book> findByTitleContaining(String title) ;
}