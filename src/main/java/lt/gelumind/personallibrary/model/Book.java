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
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private Integer book_id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private int year;

    @Column(name = "author_id")
    private Integer author_id;

    @ManyToMany(mappedBy = "books", cascade = { CascadeType.ALL})
    private List<Author> authors = new ArrayList<Author>();

    public Book() {
    }

    public Integer getBookId() {
        return book_id;
    }

    public void setBookId(Integer book_id) {
        this.book_id = book_id;
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
