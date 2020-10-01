package ToMany;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 01.10.2020
 */
@Entity
@Table(name = "car_brand")
public class CarBrand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Column(name = "id_model")
    private List<CarModel> models = new ArrayList<>();

    public static CarBrand of(String name) {
        CarBrand carBrand = new CarBrand();
        carBrand.name = name;
        return carBrand;
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

    public List<CarModel> getModels() {
        return models;
    }

    public void setModels(List<CarModel> models) {
        this.models = models;
    }

    public void addModel(CarModel model) {
        models.add(model);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarBrand carBrand = (CarBrand) o;
        return id == carBrand.id &&
                Objects.equals(name, carBrand.name) &&
                Objects.equals(models, carBrand.models);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, models);
    }
}
