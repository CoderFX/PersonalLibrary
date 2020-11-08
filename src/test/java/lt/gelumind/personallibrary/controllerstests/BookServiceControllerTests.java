package lt.gelumind.personallibrary.controllerstests;

import lt.gelumind.personallibrary.AbstractTest;
import lt.gelumind.personallibrary.model.Author;
import lt.gelumind.personallibrary.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookServiceControllerTests extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    // GET API Test
    @Test
    @Order(1)
    public void getBookListTest() throws Exception {
        String uri = "/api/book";
        MvcResult mvcResult = mvc.
                perform(
                        MockMvcRequestBuilders.get(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();

        assertEquals(200, status);

        String content = mvcResult
                .getResponse()
                .getContentAsString();

        Book[] booklist = super
                .mapFromJson(content, Book[].class);

        assertTrue(booklist.length == 0);
    }

    // POST API test
    @Test
    public void addBookTest() throws Exception {
        String uri = "/api/book";
        Book book = new Book("Ginger", 2007);
        Author author = new Author("Wannabe", "Writer");
        List<Author> authorList = new ArrayList<>();
        authorList.add(author);
        book.setAuthors(authorList);
        book.setAuthors(author);
//        Long id = (long) 3;
//        book.setId(id);
//        book.setTitle("Ginger");
//        book.setYear(2007);

        String inputJson = super.mapToJson(book);

//        String inputJson = "{\"title\":\"Name of the Wind\",\"year\":2007}";

        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson))
                .andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();

        assertEquals(200, status);

        String content = mvcResult
                .getResponse()
                .getContentAsString();

        Book[] booklist = super
                .mapFromJson(content, Book[].class);

        assertEquals(content, booklist.length == 1);
    }

    // PUT API Test
    @Test
    public void updateBookTest() throws Exception {
        String uri = "/api/book";
        Book book = new Book();
        book.setTitle("Lemon");

        String inputJson = super.mapToJson(book);
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();

        assertEquals(200, status);
        String content = mvcResult
                .getResponse()
                .getContentAsString();

        assertEquals(content, HttpStatus.ACCEPTED);
    }

    // Delete API Test
    @Test
    public void deleteBookTest() throws Exception {
        String uri = "/api/book/1";

        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.delete(uri))
                .andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();

        assertEquals(200, status);
        String content = mvcResult
                .getResponse()
                .getContentAsString();

        assertEquals(content, HttpStatus.NO_CONTENT);
    }
}
