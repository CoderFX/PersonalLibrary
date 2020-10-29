package lt.gelumind.personallibrary.controllerstests;

import lt.gelumind.personallibrary.PersonallibraryApplication;
import lt.gelumind.personallibrary.model.Author;
import lt.gelumind.personallibrary.model.Book;
import lt.gelumind.personallibrary.model.Books;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = PersonallibraryApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Sql({"classpath:schema.sql", "classpath:data.sql"})
    @Test
    public void testAllBooks() {
        assertTrue(
                this.restTemplate
                        .getForObject("http://localhost:" + port + "/books", Books.class)
                        .getBookList().size() == 3);
    }

    @Test
    public void testAddAuthor() {
        Author author = new Author("Patrick", "Rothfuss");
        Book book = new Book("Name of the Wind", 2007, author);
        ResponseEntity<String> responseEntity = this.restTemplate
                .postForEntity("http://localhost:" + port + "/books", book, String.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }

}
