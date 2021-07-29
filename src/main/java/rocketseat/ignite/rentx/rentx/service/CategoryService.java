package rocketseat.ignite.rentx.rentx.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.ignite.rentx.rentx.dto.CategoryDTO;
import rocketseat.ignite.rentx.rentx.dto.mapper.CategoryMapper;
import rocketseat.ignite.rentx.rentx.entity.Category;
import rocketseat.ignite.rentx.rentx.exception.CategoryAlreadyRegisteredException;
import rocketseat.ignite.rentx.rentx.exception.CategoryNotFoundException;
import rocketseat.ignite.rentx.rentx.repository.CategoryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    public CategoryDTO create(CategoryDTO categoryDTO) throws CategoryAlreadyRegisteredException {

        verifyIfIsAlreadyRegistered(categoryDTO.getName());

        categoryDTO.setId(UUID.randomUUID().toString());
        categoryDTO.setCreatedAt(LocalDateTime.now());

        Category categoryToSave = categoryMapper.toModel(categoryDTO);

        this.categoryRepository.save(categoryToSave);

        return categoryDTO;
    }

    public List<CategoryDTO> listAll() {
        List<Category> allCategory = this.categoryRepository.findAll();

        return allCategory.stream()
                .map(categoryMapper::toDTO)
                .collect(Collectors.toList());
    }


    public CategoryDTO findById(String id) throws CategoryNotFoundException {
        Category category = verifyIfCategoryExists(id);

        return categoryMapper.toDTO(category);
    }

    public CategoryDTO updateById(String id, Optional<CategoryDTO> categoryDTO) throws CategoryNotFoundException, CategoryAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(categoryDTO.get().getName());
        Category categoryToUpdate = verifyIfCategoryExists(id);

        categoryToUpdate.setDescription(categoryDTO.get().getDescription());
        categoryToUpdate.setName(categoryDTO.get().getName());


        this.categoryRepository.save(categoryToUpdate);

        return categoryMapper.toDTO(categoryToUpdate);
    }

    private Category verifyIfCategoryExists(String id) throws CategoryNotFoundException {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    private void verifyIfIsAlreadyRegistered(String name) throws CategoryAlreadyRegisteredException {

        Optional<Category> categoryAlreadyRegistered = this.categoryRepository.findByName(name);

        if (categoryAlreadyRegistered.isPresent()) {
            throw new CategoryAlreadyRegisteredException(name);
        }
    }

    public void deleteById(String id) throws CategoryNotFoundException {
        verifyIfCategoryExists(id);
        this.categoryRepository.deleteById(id);
    }

    public CategoryDTO findByName(String name) throws CategoryNotFoundException {
        Category foundCategory = categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException(name));
        return categoryMapper.toDTO(foundCategory);
    }
}
