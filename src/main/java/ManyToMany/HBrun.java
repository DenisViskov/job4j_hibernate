package ManyToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Class is an HBrun
 *
 * @author Денис Висков
 * @version 1.0
 * @since 01.10.2020
 */
public class HBrun {
    /**
     * Registry
     */
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure()
            .build();

    /**
     * Session Factory
     */
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata()
            .buildSessionFactory();

    public static void main(String[] args) {
        Book book = Book.of("first");
        Author first = Author.of("Ivan");
        Author second = Author.of("Sergey");
        first.addBook(book);
        second.addBook(book);
        HBrun hBrun = new HBrun();
        hBrun.addBook(book);
        hBrun.addAuthor(first);
        hBrun.addAuthor(second);
        hBrun.deleteAuthor(second);
    }

    /**
     * Method add author to DB
     *
     * @param author
     * @return Author
     */
    public Author addAuthor(Author author) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(author);
        session.getTransaction().commit();
        session.close();
        return author;
    }

    /**
     * Method of delete author from DB
     *
     * @param author
     */
    public void deleteAuthor(Author author) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.remove(author);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Method add book to DB
     *
     * @param book
     * @return Book
     */
    public Book addBook(Book book) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
        return book;
    }
}
