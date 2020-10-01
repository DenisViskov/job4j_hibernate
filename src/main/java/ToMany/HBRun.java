package ToMany;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Class is an HBRun
 *
 * @author Денис Висков
 * @version 1.0
 * @since 01.10.2020
 */
public class HBRun {
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
        CarBrand carBrand = CarBrand.of("Toyota");
        carBrand.addModel(CarModel.of("Camry"));
        carBrand.addModel(CarModel.of("Corolla"));
        carBrand.addModel(CarModel.of("Celica"));
        carBrand.addModel(CarModel.of("Crown"));
        carBrand.addModel(CarModel.of("Land cruiser"));
        new HBRun().addBrand(carBrand);
    }

    /**
     * Method add brand to DB
     *
     * @param brand
     * @return CarBrand
     */
    public CarBrand addBrand(CarBrand brand) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(brand);
        session.getTransaction().commit();
        session.close();
        return brand;
    }

    /**
     * Method add model to DB
     *
     * @param model
     * @return CarModel
     */
    public CarModel addModel(CarModel model) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(model);
        session.getTransaction().commit();
        session.close();
        return model;
    }
}
