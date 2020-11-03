package lt.gelumind.personallibrary.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "year", nullable = false)
    private int year;

    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Author> authors = new ArrayList<Author>();

    public Book() {
    }

    public Book(String title, int year, Author author) {
        super();
        this.title = title;
        this.year = year;
    }

    public Book(String title, int year) {
        super();
        this.title = title;
        this.year = year;
    }

    public Long getBookId() {
        return id;
    }

    public void setBookId(Long id) {
        this.id = id;
    }

    public String getBookTitle() {
        return title;
    }

    public void setBookTitle(String title) {
        this.title = title;
    }

    public void setAuthors(Author author) {
        this.authors.add(author) ;
    }

    public List<Author> getAuthors() {
        return this.authors;
    }

}
