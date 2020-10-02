package LazyInitializationexception;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Class is a HBrun
 *
 * @author Денис Висков
 * @version 1.0
 * @since 02.10.2020
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

    public static void main(String[] args) {
        List<BrandCar> brands;
        HbRun run = new HbRun();
        BrandCar brand = BrandCar.of("Toyota");
        ModelCar first = ModelCar.of("Celica");
        first.setCarBrand(brand);
        ModelCar second = ModelCar.of("Corolla");
        second.setCarBrand(brand);
        brand.addModel(first);
        brand.addModel(second);
        run.addBrand(brand);
        run.addModel(first);
        run.addModel(second);
        brands = run.getBrandsWithModels();
        brands.forEach(brandCar -> brandCar.getModels()
                .forEach(modelCar -> System.out.println(modelCar.getName()
                )));
    }

    /**
     * Method add model to DB
     *
     * @param modelCar
     * @return ModelCar
     */
    public ModelCar addModel(ModelCar modelCar) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(modelCar);
        session.getTransaction().commit();
        session.close();
        return modelCar;
    }

    /**
     * method add brand to db
     *
     * @param brandCar
     * @return BrandCar
     */
    public BrandCar addBrand(BrandCar brandCar) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(brandCar);
        session.getTransaction().commit();
        session.close();
        return brandCar;
    }

    /**
     * Method return all brand from DB with models
     *
     * @return List
     */
    public List getBrandsWithModels() {
        Session session = sf.openSession();
        session.beginTransaction();
        var list = session.createQuery("select distinct brands from BrandCar brands join fetch brands.models").list();
        session.getTransaction().commit();
        session.close();
        return list;
    }
}
