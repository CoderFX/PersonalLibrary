package lt.gelumind.personallibrary.controllerstests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lt.gelumind.personallibrary.model.Authors;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lt.gelumind.personallibrary.controller.AuthorRestController;
import lt.gelumind.personallibrary.dao.AuthorRepository;
import lt.gelumind.personallibrary.model.Author;

@ExtendWith(MockitoExtension.class)
public class AuthorControllerUnitTests {
    @InjectMocks
    AuthorRestController authorRestController;

    @Mock
    AuthorRepository authorRepository;

    @Test
    public void testAddAuthor()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Author author = new Author();
        author.setAuthorId(1);
        when(authorRepository.save(any(Author.class))).thenReturn(author);

        Author authorToAdd = new Author("Patrick", "Rothfuss");
        ResponseEntity<Object> responseEntity = authorRestController.addAuthor(authorToAdd);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath())
                .isEqualTo("/1");
    }

    @Test
    public void testFindAllAuthors(){
        // given
        Author author1 = new Author("Patrick", "Rothfuss");
        Author author2 = new Author("Bran", "Sanders");
        List<Author> list = new ArrayList<>();
        list.addAll(Arrays.asList(author1, author2));

        when(authorRepository.findAll())
                .thenReturn(list);

        // when
        Authors result = authorRestController.getAuthors();

        // then
        assertThat(result.getAuthorList().size())
                .isEqualTo(2);

        assertThat(result.getAuthorList().get(0).getFirstName())
                .isEqualTo(author1.getFirstName());

        assertThat(result.getAuthorList().get(1).getFirstName())
                .isEqualTo(author2.getFirstName());
    }
}
