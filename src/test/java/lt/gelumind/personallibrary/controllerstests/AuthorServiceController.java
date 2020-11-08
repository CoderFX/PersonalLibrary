package lt.gelumind.personallibrary.controllerstests;

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

public class AuthorServiceController extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Sql({"classpath:schema.sql", "classpath:data.sql"})

    @After

    public int getAuthorListLength() throws Exception {
        String uri = "/api/authors";

        MvcResult mvcResult = mvc.
                perform(
                        MockMvcRequestBuilders.get(uri)
                                .accept(MediaType.APPLICATION_JSON_VALUE)
                )
                .andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();

        String content = mvcResult
                .getResponse()
                .getContentAsString();

        Author[] authorList = super
                .mapFromJson(content, Author[].class);

        return authorList.length;
    }

    // GET API Test
    @Test
    public void step1GetAuthorListTest() throws Exception {
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

        // Number of authors should be 0

        Author[] authorList = super
                .mapFromJson(content, Author[].class);

        assertTrue(authorList.length == 0);
    }

    // POST API test
    @Test
    public void step2AddAuthorTest() throws Exception {
        // given
        String uri = "/api/author";

        // when
        Author author = new Author("Wannabe", "Writer");

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

        // Number of authors should be 1
        assertTrue(getAuthorListLength() == 2);

//        String content = mvcResult
//                .getResponse()
//                .getContentAsString();
//
//        Author[] authorList = super
//                .mapFromJson(content, Author[].class);
//
//        HttpStatus result = CREATED;
//
//        assertEquals(content, result);
    }

    // PUT API Test
    @Test
    public void step3UpdateAuthorTest() throws Exception {
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

        // Number of authors should be 1
        assertTrue(getAuthorListLength() == 1);

//        String content = mvcResult
//                .getResponse()
//                .getContentAsString();
//
//        assertEquals(content, HttpStatus.ACCEPTED);
    }

    // Delete API Test
    @Test
    public void step4DeleteAuthorTest() throws Exception {
        String uri = "/api/author/1";

        MvcResult mvcResult = mvc
                .perform(MockMvcRequestBuilders.delete(uri))
                .andReturn();

        int status = mvcResult
                .getResponse()
                .getStatus();

        // Test of status is 200 - OK
        assertEquals(200, status);

        // Number of authors should be 0
        assertTrue(getAuthorListLength() == 1);

//        String content = mvcResult
//                .getResponse()
//                .getContentAsString();
//
//        assertEquals(content, HttpStatus.NO_CONTENT);
    }
}
