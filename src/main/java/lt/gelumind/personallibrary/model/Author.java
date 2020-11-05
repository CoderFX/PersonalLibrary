package lt.gelumind.personallibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.objects.annotations.Constructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Component
@Table(name = "authors")
public class Author implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long id;

    @Column(name = "firstName", nullable = false)
    @NotEmpty
    private String firstName;

    @Column(name = "lastName", nullable = false)
    @NotEmpty
    private String lastName;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name="book_authors",
            joinColumns = @JoinColumn(name="author_id", referencedColumnName = "author_id"),
            inverseJoinColumns = @JoinColumn(name="book_id", referencedColumnName = "book_id")
    )
    private List<Book> books = new ArrayList<Book>();

    public Author() {}


    public Author(@NotEmpty String firstName, @NotEmpty String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author(@NotEmpty String firstName, @NotEmpty String lastName, List<Book> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

//    public void setBooks(List<Book> books) {
//        this.books = books;
//    }

    @Override
    public String toString() {
        return "Author [author_id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + "]";
    }
}
