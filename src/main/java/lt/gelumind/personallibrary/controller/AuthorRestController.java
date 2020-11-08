package lt.gelumind.personallibrary.controller;

import lombok.RequiredArgsConstructor;
import lt.gelumind.personallibrary.dao.AuthorRepository;
import lt.gelumind.personallibrary.model.Author;
import lt.gelumind.personallibrary.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class AuthorRestController extends ApiRestController {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    AuthorService authorService;

    // Get all authors
    @GetMapping(value = "/authors", produces = "application/json")
    public List<Author> getAll() {
        return authorService.getAllAuthors() ;
    }

    // Get author by ID
    // {{url}}/api/author?id=VALUE
    @GetMapping(value = "/author/{id}", produces = "application/json")
    public @ResponseBody
    Optional<Author> getAuthorById(@PathVariable("id") Long id) {
        return authorService.getById(id);
    }

    // Get author by first name
    // {{url}}/api/author?firstName=VALUE
    @GetMapping(value = "/authors/firstName/{firstName}")
    public @ResponseBody
    List<Author> getAuthorByFirstName(@PathVariable String firstName) {
        return authorService.getByFirstName(firstName.replace('+', ' ')) ;
    }

    // Get author by last name
    @GetMapping(value = "/authors/lastName/{lastName}", produces = "application/json")
    public @ResponseBody
    List<Author> getAuthorByLastName(@PathVariable String lastName) {
        return authorService.getByLastName(lastName.replace('+', ' ')) ;
    }

    // Add author
    @PostMapping(value = "/author", consumes = "application/json", produces = "application/json")
    public HttpStatus addAuthor(@RequestBody Author author) {
        return authorService.addAuthor(author) ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST ;
    }

    // Update author
    @PutMapping(value = "/author", consumes = "application/json", produces = "application/json")
    public HttpStatus updateAuthor(@RequestBody Author author) {
        return authorService.updateAuthor(author) ? HttpStatus.ACCEPTED : HttpStatus.BAD_REQUEST ;
    }

    // Delete Author by ID
    @DeleteMapping(value = "/author/{id}", produces = "application/json")
    public HttpStatus deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return HttpStatus.NO_CONTENT; //TODO if entry does not exist - application crashes - fix it
    }

//    // Get authorList
//    @GetMapping(path="/authors", produces = "application/json")
//    public Authors getAuthors() {
//        Authors response = new Authors();
//        ArrayList<Author> list = new ArrayList<>();
//        authorRepository.findAll().forEach(e -> list.add(e));
//        response.setAuthorList(list);
//        return response;
//    }
//
//    // Add author
//    @PostMapping(path= "/authors", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<Object> addAuthor(@RequestBody Author author) {
//
//        // add resource
//        author = authorRepository.save(author);
//
//        // Create resource location
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(author.getAuthorId())
//                .toUri();
//
//        // Send location in response
//        return ResponseEntity.created(location).build();
//    }
}
