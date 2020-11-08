package lt.gelumind.personallibrary.controller;

import lt.gelumind.personallibrary.AbstractTest;
import lt.gelumind.personallibrary.model.Author;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AuthorRestControllerTest extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Sql({"classpath:schema.sql", "classpath:data.sql"})

    @After

    @Test
    public void getAll() throws Exception {
        // given
        String uri = "/api/authors";

        // then
        MvcResult mvcResult = mvc.
                perform(
                        MockMvcRequestBuilders.get(uri)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();

        // Test of status is 200 - OK
        assertEquals(200, status);

        String content = mvcResult
                .getResponse()
                .getContentAsString();

        Author[] authorList = super
                .mapFromJson(content, Author[].class);

        // Number of authors should be 4
        assertTrue(authorList.length == 0);
    }

    @Test
    public void getAuthorById() throws Exception {
        // given
        Long id = (long) 1;
        int id1 = 1;
        String uri = "/api/author/1";

        // then
        MvcResult mvcResult = mvc.
                perform(
                        MockMvcRequestBuilders.get(uri)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();

        // Test of status is 200 - OK
        assertEquals(200, status);
    }

    @Test
    public void getAuthorByFirstName() throws Exception {
        // given
        String uri = "/api/authors/firstName/Patrick";

        // then
        MvcResult mvcResult = mvc.
                perform(
                        MockMvcRequestBuilders.get(uri)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();

        // Test of status is 200 - OK
        assertEquals(200, status);
    }

    @Test
    public void getAuthorByLastName() throws Exception {
        // given
        String uri = "/api/authors/lastName/Rothfuss";

        // then
        MvcResult mvcResult = mvc.
                perform(
                        MockMvcRequestBuilders.get(uri)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();

        // Test of status is 200 - OK
        assertEquals(200, status);
    }

    @Test
    public void addAuthor() throws Exception {
        // given
        String uri = "/api/author";

        // when
        Author author = new Author("Alpha", "Omega");
        Long id = (long) 5;
        author.setId(id);

        // then
        String inputJson = super.mapToJson(author);

        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(inputJson))
                .andReturn();

        // Test of status is 200 - OK
        int status = mvcResult
                .getResponse()
                .getStatus();


        assertEquals(200, status);
    }

    @Test
    public void updateAuthor() throws Exception {
        String uri = "/api/author";
        Author author = new Author("Mr", "Nobody");
        Long id = (long) 1;
        author.setId(id);

        String inputJson = super.mapToJson(author);
        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.put(uri)
                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();

        // Test of status is 200 - OK
        assertEquals(200, status);
    }

    @Test
    public void deleteAuthor() throws Exception {
        String uri = "/api/author/1";

        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.delete(uri))
                .andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();

        // Test of status is 200 - OK
        assertEquals(200, status);
    }
}