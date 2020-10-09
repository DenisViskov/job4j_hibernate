package HQL;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

/**
 * Class is an HbRunCandidate
 *
 * @author Денис Висков
 * @version 1.0
 * @since 09.10.2020
 */
public class HbRunCandidate {
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
        HbRunCandidate runCandidate = new HbRunCandidate();
        Candidate first = new Candidate(0, "first", 5, 5000);
        Candidate second = new Candidate(0, "second", 3, 3000);
        Candidate third = new Candidate(0, "third", 2, 2000);
        runCandidate.save(first);
        runCandidate.save(second);
        runCandidate.save(third);
        System.out.println(runCandidate.getById(1).getName());
        System.out.println(runCandidate.getByName("second").get(0).getName());
        first.setName("notFirst");
        runCandidate.update(first);
        System.out.println(runCandidate.getById(1).getName());
        runCandidate.delete(second);
        System.out.println(runCandidate.getById(2));
        runCandidate.insert(second);
        System.out.println(runCandidate.getByName("notFirstsecond").get(0).getName());
    }

    /**
     * Standard save candidate using Hibernate API
     *
     * @param candidate
     * @return Candidate
     */
    public Candidate save(Candidate candidate) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(candidate);
        session.getTransaction().commit();
        session.close();
        return candidate;
    }

    /**
     * Method looking for candidate by given id using HQL query
     *
     * @param id
     * @return Candidate
     */
    public Candidate getById(int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        Candidate candidate = (Candidate) session.createQuery("from Candidate c where c.id= :id")
                .setParameter("id", id)
                .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return candidate;
    }

    /**
     * Method return list Candidate by given name using HQL query
     *
     * @param name
     * @return List
     */
    public List<Candidate> getByName(String name) {
        Session session = sf.openSession();
        session.beginTransaction();
        List candidates = session.createQuery("from Candidate c where c.name= :name")
                .setParameter("name", name)
                .list();
        session.getTransaction().commit();
        session.close();
        return candidates;
    }

    /**
     * Method updates candidate in DB using HQL query
     *
     * @param candidate
     */
    public void update(Candidate candidate) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.createQuery("update Candidate c set c.name = :name, c.experience = :experience, c.salary = :salary where c.id = :id")
                .setParameter("name", candidate.getName())
                .setParameter("experience", candidate.getExperience())
                .setParameter("salary", candidate.getSalary())
                .setParameter("id", candidate.getId())
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Method execute delete given candidate from DB using HQL query
     *
     * @param candidate
     */
    public void delete(Candidate candidate) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.createQuery("delete from Candidate where id = :id")
                .setParameter("id", candidate.getId())
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Method execute insert into DB based on existing record
     *
     * @param candidate
     */
    public void insert(Candidate candidate) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.createQuery("insert into Candidate (name,experience,salary)" +
                "select concat(name,:name) , experience + 5, salary " +
                "from Candidate c where c.id = :id")
                .setParameter("name", candidate.getName())
                .setParameter("id", 1)
                .executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
