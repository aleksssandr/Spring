package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;
@Service
public class CarService {

    private List<Car> cars;

    public CarService() {
        cars = new ArrayList<Car>();
        cars.add(new Car("BMW", "X-6", 1999));
        cars.add(new Car("Audi", "X-3", 1995));
        cars.add(new Car("Honda", "X-2", 1990));
        cars.add(new Car("Opel", "X-1", 1991));
        cars.add(new Car("Lada", "X-8", 1992));
    }

    public List<Car> getCarsByCount(int count) {
        if (count > cars.size()) {
            return cars;
        } else {
            return cars.subList(0, count);
        }
    }
}
