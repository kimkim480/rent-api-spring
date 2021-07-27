package rocketseat.ignite.rentx.rentx.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.ignite.rentx.rentx.entity.Category;
import rocketseat.ignite.rentx.rentx.repository.CategoryRepository;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    public Category create(Category category) {
        category.setId(UUID.randomUUID().toString());
        category.setCreatedAt(LocalDateTime.now());

        return this.categoryRepository.save(category);
    }
}
