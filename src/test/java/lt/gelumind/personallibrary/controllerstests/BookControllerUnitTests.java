//package lt.gelumind.personallibrary.controllerstests;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//
//import lt.gelumind.personallibrary.model.Book;
//import lt.gelumind.personallibrary.model.Books;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mock.web.MockHttpServletRequest;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import lt.gelumind.personallibrary.controller.BookRestController;
//import lt.gelumind.personallibrary.dao.BookRepository;
//
//@ExtendWith(MockitoExtension.class)
//public class BookControllerUnitTests {
//    @InjectMocks
//    BookRestController bookRestController;
//
//    @Mock
//    BookRepository bookRepository;
//
//    @Test
//    public void testAddBook()
//    {
//        MockHttpServletRequest request = new MockHttpServletRequest();
//        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
//
//        Book book = new Book();
//        book.setBookId(1);
//        when(bookRepository.save(any(Book.class)))
//                .thenReturn(book);
//
//        Book bookToAdd = new Book("Name of the Wind", 2007);
//        ResponseEntity<Object> responseEntity = bookRestController.addBook(bookToAdd);
//
//        assertThat(responseEntity.getStatusCodeValue())
//                .isEqualTo(201);
//        assertThat(responseEntity.getHeaders().getLocation().getPath())
//                .isEqualTo("/1");
//    }
//
//    @Test
//    public void testFindAllBooks()
//    {
//        // given
//        Book book1 = new Book("Name of the wind", 2007);
//        Book book2 = new Book("The Wise Man's Fear", 2011);
//        List<Book> list = new ArrayList<>();
//        list.addAll(Arrays.asList(book1, book2));
//
//        when(bookRepository.findAll()).thenReturn(list);
//
//        // when
//        Books result = bookRestController.getAll();
//
//        // then
//        assertThat(result.getBookList().size()).isEqualTo(2);
//
//        assertThat(result.getBookList().get(0).getBookTitle())
//                .isEqualTo(book1.getBookTitle());
//
//        assertThat(result.getBookList().get(1).getBookTitle())
//                .isEqualTo(book2.getBookTitle());
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
