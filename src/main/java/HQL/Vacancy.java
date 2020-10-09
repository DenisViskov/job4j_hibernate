package HQL;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 09.10.2020
 */
@Entity
@Table(name = "vacancy")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    public Vacancy() {
    }

    public Vacancy(int id, String name) {
        this.id = id;
        this.name = name;
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
        Vacancy vacancy = (Vacancy) o;
        return id == vacancy.id &&
                Objects.equals(name, vacancy.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
