package rocketseat.ignite.rentx.rentx.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rocketseat.ignite.rentx.rentx.dto.CategoryDTO;
import rocketseat.ignite.rentx.rentx.dto.mapper.CategoryMapper;
import rocketseat.ignite.rentx.rentx.entity.Category;
import rocketseat.ignite.rentx.rentx.exception.CategoryAlreadyRegisteredException;
import rocketseat.ignite.rentx.rentx.exception.CategoryNotFoundException;
import rocketseat.ignite.rentx.rentx.helper.CSVHelper;
import rocketseat.ignite.rentx.rentx.repository.CategoryRepository;

import java.io.IOException;
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

        categoryRepository.save(categoryToSave);

        return categoryDTO;
    }

    public List<CategoryDTO> listAll() {
        List<Category> allCategory = categoryRepository.findAll();

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


        categoryRepository.save(categoryToUpdate);

        return categoryMapper.toDTO(categoryToUpdate);
    }

    public void deleteById(String id) throws CategoryNotFoundException {
        verifyIfCategoryExists(id);
        categoryRepository.deleteById(id);
    }

    public CategoryDTO findByName(String name) throws CategoryNotFoundException {
        Category foundCategory = categoryRepository.findByName(name)
                .orElseThrow(() -> new CategoryNotFoundException(name));
        return categoryMapper.toDTO(foundCategory);
    }

    public void importCSV(MultipartFile file) {
        try {
            List<Category> categories = CSVHelper.csvToCategory(file.getInputStream());
            categoryRepository.saveAll(categories);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

    private Category verifyIfCategoryExists(String id) throws CategoryNotFoundException {
        return this.categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    private void verifyIfIsAlreadyRegistered(String name) throws CategoryAlreadyRegisteredException {

        Optional<Category> categoryAlreadyRegistered = categoryRepository.findByName(name);

        if (categoryAlreadyRegistered.isPresent()) {
            throw new CategoryAlreadyRegisteredException(name);
        }
    }
}
