package LazyInitializationexception;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class is a brand of car
 *
 * @author Денис Висков
 * @version 1.0
 * @since 02.10.2020
 */
@Entity
@Table(name = "BrandCar")
public class BrandCar {
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
     * Models
     */
    @OneToMany(mappedBy = "brandCar")
    @Column(name = "model_id")
    private List<ModelCar> models = new ArrayList<>();

    public static BrandCar of(String name) {
        BrandCar brandCar = new BrandCar();
        brandCar.name = name;
        return brandCar;
    }

    /**
     * Method add model to models
     *
     * @param model
     */
    public void addModel(ModelCar model) {
        models.add(model);
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

    public List<ModelCar> getModels() {
        return models;
    }

    public void setModels(List<ModelCar> models) {
        this.models = models;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BrandCar brandCar = (BrandCar) o;
        return id == brandCar.id &&
                Objects.equals(name, brandCar.name) &&
                Objects.equals(models, brandCar.models);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, models);
    }
}
