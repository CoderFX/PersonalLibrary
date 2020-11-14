package lt.gelumind.personallibrary.controller;

import lombok.RequiredArgsConstructor;
import lt.gelumind.personallibrary.dao.BookRepository;
import lt.gelumind.personallibrary.model.Author;
import lt.gelumind.personallibrary.model.Book;
import lt.gelumind.personallibrary.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class BookRestController extends ApiRestController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    BookService bookService;

    // Get all books
    @GetMapping(value = "/book", produces = "application/json")
    public List<Book> getAll() {
        return bookService.getAllBooks();
    }

    // Get book by ID
    @GetMapping(value = "/book/{id}", produces = "application/json")
    public @ResponseBody
    Optional<Book> getBookById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    // Get book by title
    @GetMapping(value = "/book/title?{title}", produces = "application/json")
    public List<Book> getBookByTitle(@PathVariable String title) {
        return bookService.getByTitle(title.replace('+', ' '));
    }

    // Add book
    @PostMapping(value = "/book", consumes = "application/json", produces = "application/json")
    public HttpStatus addBook(@RequestBody Book book) {
        return bookService.addBook(book) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
    }

//    @PostMapping("/addBookAndAuthor")
//    public void addBookAndAuthor(@RequestBody RequestObject formosDuomenys) {
//        List<Author> authorList = formosDuomenys.getAuthorList();
//        author.setBookAuthors(authorList);
//
//        Author author = new Author();
//        Book book = new Book();
//        author.setAuthorName(requestObject.getAuthorName());
//        authorService.saveAuthor(author);
//        book.setBookTitle(requestObject.getBookTitle());
//        book.setAuthor(author.getId());
//        bookService.saveBook(book);
//    }

    // Update book
    @PutMapping(value = "/book", consumes = "application/json", produces = "application/json")
    public HttpStatus updateBook(@RequestBody Book book) {
        return bookService.updateBook(book) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
    }

    // Delete book
    @DeleteMapping(value = "/book/{id}", produces = "application/json")
    public HttpStatus deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return HttpStatus.NO_CONTENT;
    }

    // Add author to book
    // Since book can have many authors we need this to add multiple authors
    // when adding a new book. Vice versa is not recommended
    @PutMapping(value = "/book/{bookId}/author/{authorId}", produces = "application/json")
    public HttpStatus addAuthor(@PathVariable Long bookId, @PathVariable Long authorId) {
        return bookService.addAuthor(bookId, authorId) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST;
    }

//    @GetMapping(path="/books", produces = "application/json")
//    public Books getBooks() {
//        Books response = new Books();
//        ArrayList<Book> list = new ArrayList<>();
//        bookRepository.findAll().forEach(e -> list.add(e));
//        response.setBookList(list);
//        return response;
//    }
}
