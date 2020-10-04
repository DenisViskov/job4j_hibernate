package CarsAndOwners;

import org.junit.Test;

import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class HbRunTest {

    private final HbRun hbRun = new HbRun();

    @Test
    public void addCarTest() {
        Car car = new Car();
        car.setName("audi");
        Engine engine = new Engine();
        engine.setName("V8");
        car.setEngine(engine);
        Driver first = new Driver("Ivan");
        Driver second = new Driver("Sergey");
        car.addDriver(first);
        car.addDriver(second);
        hbRun.addEngine(engine);
        Car out = hbRun.addCar(car);
        assertThat(out.getName(), is(car.getName()));
        assertThat(out.getDrivers().isEmpty(), is(false));
        hbRun.deleteAllContent();
    }

    @Test
    public void updateCarTest() {
        Car car = new Car();
        car.setName("toyota");
        Engine engine = new Engine();
        engine.setName("V4");
        car.setEngine(engine);
        hbRun.addEngine(engine);
        Car out = hbRun.addCar(car);
        out.setName("mazda");
        hbRun.updateCar(out);
        out = hbRun.get(Car.class, out.getId());
        assertThat(out.getName(), is("mazda"));
    }

    @Test
    public void deleteCarTest() {
        Car car = new Car();
        car.setName("reno");
        Engine engine = new Engine();
        engine.setName("V8");
        car.setEngine(engine);
        hbRun.addEngine(engine);
        Car out = hbRun.addCar(car);
        hbRun.deleteCar(out);
        out = hbRun.get(Car.class, out.getId());
        assertNull(out);
    }

    @Test
    public void addDriverTest() {
        hbRun.deleteAllContent();
    }

    @Test
    public void updateDriverTest() {
    }

    @Test
    public void deleteDriverTest() {
    }

    @Test
    public void addEngineTest() {
    }

    @Test
    public void updateEngineTest() {
    }

    @Test
    public void deleteEngineTest() {
    }
}