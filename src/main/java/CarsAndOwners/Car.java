package CarsAndOwners;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class is a car
 *
 * @author Денис Висков
 * @version 1.0
 * @since 04.10.2020
 */
@Entity
@Table(name = "auto")
public class Car {
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
     * Engine
     */
    @ManyToOne
    @JoinColumn(name = "engine_id")
    private Engine engine;
    /**
     * Drivers
     */
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Column(name = "driver_id")
    private Set<Driver> drivers = new HashSet<>();

    public Car() {
    }

    /**
     * Method add driver to drivers set
     *
     * @param driver
     */
    public void addDriver(Driver driver) {
        drivers.add(driver);
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

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id &&
                Objects.equals(name, car.name) &&
                Objects.equals(engine, car.engine) &&
                Objects.equals(drivers, car.drivers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, engine, drivers);
    }
}
