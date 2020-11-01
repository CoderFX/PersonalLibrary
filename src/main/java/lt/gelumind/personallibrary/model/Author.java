package lt.gelumind.personallibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer author_id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="writtenBy",
            joinColumns = @JoinColumn(name="author_id", referencedColumnName = "author_id"),
            inverseJoinColumns = @JoinColumn(name="book_id", referencedColumnName = "book_id")
    )
    private List<Book> books = new ArrayList<Book>();

    public Author() {}

    public Author(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getAuthorId() {
        return author_id;
    }

    public void setAuthorId(Integer author_id) {
        this.author_id = author_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Book> getBooks() {
        return this.books;
    }

    public void setBooks(Book book) {
        this.books.add(book);
    }

    @Override
    public String toString() {
        return "Author [author_id=" + author_id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
