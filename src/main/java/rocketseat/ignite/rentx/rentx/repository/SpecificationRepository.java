package rocketseat.ignite.rentx.rentx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rocketseat.ignite.rentx.rentx.entity.Specification;

import java.util.Optional;

public interface SpecificationRepository extends JpaRepository<Specification, String> {

    Optional<Specification> findByName(String name);
}
