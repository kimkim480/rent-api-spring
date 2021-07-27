package rocketseat.ignite.rentx.rentx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rocketseat.ignite.rentx.rentx.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
}
