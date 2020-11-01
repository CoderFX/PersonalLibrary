package lt.gelumind.personallibrary.controller;

import lt.gelumind.personallibrary.dao.AuthorRepository;
import lt.gelumind.personallibrary.model.Author;
import lt.gelumind.personallibrary.model.Authors;
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
public class AuthorRestController extends ApiRestController {
    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping(path="/authors", produces = "application/json")
    public Authors getAuthors() {
        Authors response = new Authors();
        ArrayList<Author> list = new ArrayList<>();
        authorRepository.findAll().forEach(e -> list.add(e));
        response.setAuthorList(list);
        return response;
    }

    @PostMapping(path= "/authors", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> addAuthor(@RequestBody Author author) {

        // add resource
        author = authorRepository.save(author);

        // Create resource location
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(author.getAuthorId())
                .toUri();

        // Send location in response
        return ResponseEntity.created(location).build();
    }
}
