package lt.gelumind.personallibrary.model;

import jdk.nashorn.internal.objects.annotations.Constructor;

import javax.validation.constraints.NotEmpty;

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
    @NotEmpty
    private String title;

    @Column(name = "year", nullable = false)
    @NotEmpty
    private int year;

    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Author> authors = new ArrayList<Author>();

    public Book() {
    }

    public Book(@NotEmpty String title, @NotEmpty int year) {
        this.title = title;
        this.year = year;
    }

    public Book(@NotEmpty String title, @NotEmpty int year, List<Author> authors) {
        this.title = title;
        this.year = year;
        this.authors = authors;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }



    public List<Author> getAuthors() {
        return this.authors;
    }

    // Working
    public void setAuthors(Author author) {
        this.authors.add(author) ;
    }

    // Also works?
//    public void setAuthors(List<Author> authors) {
//        this.authors = authors;
//    }

}
