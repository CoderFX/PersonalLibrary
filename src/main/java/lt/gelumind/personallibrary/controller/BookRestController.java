package lt.gelumind.personallibrary.controller;

import lt.gelumind.personallibrary.dao.BookRepository;
import lt.gelumind.personallibrary.model.Book;
import lt.gelumind.personallibrary.model.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;

@RestController
public class BookRestController extends ApiRestController {
    @Autowired
    private BookRepository bookRepository;

    @GetMapping(path="/books", produces = "application/json")
    public Books getBooks() {
        Books response = new Books();
        ArrayList<Book> list = new ArrayList<>();
        bookRepository.findAll().forEach(e -> list.add(e));
        response.setBookList(list);
        return response;
    }

    @PostMapping(path= "/books", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addBook(@RequestBody Book book) {

        // add resource
        book = bookRepository.save(book);

        // Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(book.getBookId())
                .toUri();

        // Send location in response
        return ResponseEntity.created(location).build();
    }
}
