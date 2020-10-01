package ManyToMany;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class is a book
 *
 * @author Денис Висков
 * @version 1.0
 * @since 01.10.2020
 */
@Entity
@Table(name = "books")
public class Book {

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

    public static Book of(String name) {
        Book book = new Book();
        book.name = name;
        return book;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
