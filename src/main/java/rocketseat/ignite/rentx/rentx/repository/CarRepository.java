package rocketseat.ignite.rentx.rentx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rocketseat.ignite.rentx.rentx.entity.Car;

public interface CarRepository extends JpaRepository<Car, String> {
}
