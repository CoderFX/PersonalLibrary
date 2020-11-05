//package lt.gelumind.personallibrary.controllerstests;
//
//import lt.gelumind.personallibrary.controller.BookRestController;
//import lt.gelumind.personallibrary.dao.BookRepository;
//import lt.gelumind.personallibrary.model.Book;
//import lt.gelumind.personallibrary.services.BookService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Collections;
//
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class BookControllerUnitTests {
//    @InjectMocks
//    private BookRestController bookRestController;
//
//    @Mock
//    private BookRepository bookRepository;
//
//    @Mock
//    private BookService bookService;
//
//    @Test
//    public void testAddBook()
//    {
//        // given
//        String title = "Name of the wind";
//        Book bookToAdd = new Book(title, 2007);
//        bookService.addBook(bookToAdd);
//
//        Long id = (long) 1;
//
//        when(bookService.getById(id))
//            .thenReturn(Collections.singletonList(bookToAdd));
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
//
