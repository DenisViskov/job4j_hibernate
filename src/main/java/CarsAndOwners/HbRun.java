package CarsAndOwners;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 04.10.2020
 */
public class HbRun {
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

    public Car addCar(Car car) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(car);
        session.getTransaction().commit();
        session.close();
        return car;
    }

    public void updateCar(Car car) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(car);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteCar(Car car) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.delete(car);
        session.getTransaction().commit();
        session.close();
    }

    public Driver addDriver(Driver driver) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(driver);
        session.getTransaction().commit();
        session.close();
        return driver;
    }

    public void updateDriver(Driver driver) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(driver);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteDriver(Driver driver) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.delete(driver);
        session.getTransaction().commit();
        session.close();
    }

    public Engine addEngine(Engine engine) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(engine);
        session.getTransaction().commit();
        session.close();
        return engine;
    }

    public void updateEngine(Engine engine) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(engine);
        session.getTransaction().commit();
        session.close();
    }

    public void deleteEngine(Engine engine) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.delete(engine);
        session.getTransaction().commit();
        session.close();
    }

    public <V> V get(Class<?> type, int id) {
        Session session = sf.openSession();
        session.beginTransaction();
        V result = (V) session.get(type, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public void deleteAllContent() {
        Session session = sf.openSession();
        session.beginTransaction();
        session.createQuery("delete from CarsAndOwners.Car").executeUpdate();
        session.createQuery("delete from CarsAndOwners.Engine").executeUpdate();
        session.createQuery("delete from CarsAndOwners.Driver").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
