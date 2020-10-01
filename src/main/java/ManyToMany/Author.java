package ManyToMany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class is an Author
 *
 * @author Денис Висков
 * @version 1.0
 * @since 01.10.2020
 */
@Entity
@Table(name = "authors")
public class Author {

    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Name
     */
    @Column(name = "name")
    private String name;

    /**
     * Books
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Column(name = "book_id")
    private List<Book> books = new ArrayList<>();

    public static Author of(String name) {
        Author author = new Author();
        author.name = name;
        return author;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id &&
                Objects.equals(name, author.name) &&
                Objects.equals(books, author.books);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, books);
    }
}
