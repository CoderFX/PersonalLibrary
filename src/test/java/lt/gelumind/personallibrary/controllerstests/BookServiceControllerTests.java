package lt.gelumind.personallibrary.controllerstests;

import lt.gelumind.personallibrary.model.Book;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

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
    public void getBookListTest() throws Exception {
        String uri = "/api/books";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Book[] booklist = super.mapFromJson(content, Book[].class);
        assertTrue(booklist.length == 0);
    }

    // POST API test
    @Test
    public void addBookTest() throws Exception {
        String uri = "/api/book";
        Book book = new Book();
        Long id = (long) 3;
        book.setId(id);
        book.setTitle("Ginger");

        String inputJson = super.mapToJson(book);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, HttpStatus.CREATED);
    }

    // PUT API Test
    @Test
    public void updateBookTest() throws Exception {
        String uri = "/api/book";
        Book book = new Book();
        book.setTitle("Lemon");

        String inputJson = super.mapToJson(book);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, HttpStatus.ACCEPTED);
    }

    // Delete API Test
    @Test
    public void deleteBookTest() throws Exception {
        String uri = "/book/1";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        assertEquals(content, HttpStatus.NO_CONTENT);
    }
}
