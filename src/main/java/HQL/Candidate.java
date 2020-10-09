package HQL;

import javax.persistence.*;
import java.util.Objects;

/**
 * Class is a candidate
 *
 * @author Денис Висков
 * @version 1.0
 * @since 09.10.2020
 */
@Entity
@Table(name = "candidate")
public class Candidate {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    /**
     * Name
     */
    @Column(name = "name")
    private String name;
    /**
     * Experience
     */
    @Column(name = "experience")
    private int experience;
    /**
     * Salary
     */
    @Column(name = "salary")
    private int salary;
    /**
     * Base
     */
    @OneToOne
    @JoinColumn(name = "base_id")
    private Base base;

    public Candidate() {
    }

    public Candidate(int id, String name, int experience, int salary, Base base) {
        this.id = id;
        this.name = name;
        this.experience = experience;
        this.salary = salary;
        this.base = base;
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

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return id == candidate.id &&
                experience == candidate.experience &&
                salary == candidate.salary &&
                Objects.equals(name, candidate.name) &&
                Objects.equals(base, candidate.base);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, experience, salary, base);
    }
}
