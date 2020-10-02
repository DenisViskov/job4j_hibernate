package LazyInitializationexception;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Денис Висков
 * @version 1.0
 * @since 02.10.2020
 */
@Entity
@Table(name = "carModels")
public class ModelCar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandCar brandCar;

    public static ModelCar of(String name) {
        ModelCar modelCar = new ModelCar();
        modelCar.name = name;
        return modelCar;
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

    public BrandCar getCarBrand() {
        return brandCar;
    }

    public void setCarBrand(BrandCar brandCar) {
        this.brandCar = brandCar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModelCar modelCar = (ModelCar) o;
        return id == modelCar.id &&
                Objects.equals(name, modelCar.name) &&
                Objects.equals(brandCar, modelCar.brandCar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, brandCar);
    }
}
