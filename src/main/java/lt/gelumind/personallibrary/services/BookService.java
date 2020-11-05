package lt.gelumind.personallibrary.services;

import lombok.RequiredArgsConstructor;
import lt.gelumind.personallibrary.dao.AuthorRepository;
import lt.gelumind.personallibrary.dao.BookRepository;
import lt.gelumind.personallibrary.model.Author;
import lt.gelumind.personallibrary.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Component
@RequiredArgsConstructor
public class BookService {
    @Autowired
    BookRepository bookRepository ;

    @Autowired
    AuthorRepository authorRepository ;

    @Transactional
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll() ;
    }

    @Transactional
    public List<Book> getByTitle(String title) {
        return (List<Book>) bookRepository.findByTitleContaining(title) ;
    }

    @Transactional
    public boolean addBook(Book book) {
        return bookRepository.save(book) != null ;
    }

    @Transactional
    public boolean updateBook(Book book) {
        return bookRepository.save(book) != null ;
    }

    @Transactional
    public Optional<Book> getById(Long id) {
        return bookRepository.findById(id) ;
    }

    @Transactional
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Transactional
    public boolean addAuthor(Long bookId, Long authorId) {
        Optional<Book> book ;
        if((book = bookRepository.findById(bookId)) != null)
        {
            Optional<Author> author ;
            if((author = authorRepository.findById(authorId)) != null)
            {
                author.get().setBooks(book.get());
                authorRepository.save(author.get());
                return true;
            }
            else return false ;
        }
        else return false ;
    }
}
