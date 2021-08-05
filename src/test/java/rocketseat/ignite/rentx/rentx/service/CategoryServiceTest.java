package rocketseat.ignite.rentx.rentx.service;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import rocketseat.ignite.rentx.rentx.builder.CategoryDTOBuilder;
import rocketseat.ignite.rentx.rentx.dto.CategoryDTO;
import rocketseat.ignite.rentx.rentx.dto.mapper.CategoryMapper;
import rocketseat.ignite.rentx.rentx.entity.Category;
import rocketseat.ignite.rentx.rentx.exception.category.CategoryAlreadyRegisteredException;
import rocketseat.ignite.rentx.rentx.exception.category.CategoryNotFoundException;
import rocketseat.ignite.rentx.rentx.repository.CategoryRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class CategoryServiceTest {

    private static final String INVALID_CATEGORY_ID = "775c1878-519b-42a6-9a92-f98201fd6771";

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private CategoryMapper categoryMapper = CategoryMapper.INSTANCE;


    @Test
    void whenCategoryInformedThenItShouldBeCreated() throws CategoryAlreadyRegisteredException {
        CategoryDTO expectedCategoryDTO = CategoryDTOBuilder.builder().build().toCategoryDTO();
        Category expectedSavedCategory = categoryMapper.toModel(expectedCategoryDTO);

        when(categoryRepository.findByName(expectedCategoryDTO.getName())).thenReturn(Optional.empty());
        when(categoryRepository.save(expectedSavedCategory)).thenReturn(expectedSavedCategory);

        CategoryDTO createdCategoryDTO = categoryService.create(expectedCategoryDTO);

        assertThat(createdCategoryDTO.getId(), is(equalTo(expectedCategoryDTO.getId())));
        assertThat(createdCategoryDTO.getName(), is(equalTo(expectedCategoryDTO.getName())));
        assertThat(createdCategoryDTO.getDescription(), is(equalTo(expectedCategoryDTO.getDescription())));
        assertThat(createdCategoryDTO.getCreatedAt(), is(equalTo(expectedCategoryDTO.getCreatedAt())));
    }

    @Test
    void whenAlreadyRegisteredCategoryInformedThenAnExceptionShouldBeThrown() {
        CategoryDTO expectedCategoryDTO = CategoryDTOBuilder.builder().build().toCategoryDTO();
        Category duplicatedCategory = categoryMapper.toModel(expectedCategoryDTO);

        when(categoryRepository.findByName(expectedCategoryDTO.getName())).thenReturn(Optional.of(duplicatedCategory));

        assertThrows(CategoryAlreadyRegisteredException.class, () -> categoryService.create(expectedCategoryDTO));
    }

    @Test
    void whenValidCategoryNameIsGivenThenReturnACategory() throws CategoryNotFoundException {
        CategoryDTO expectedCategoryDTO = CategoryDTOBuilder.builder().build().toCategoryDTO();
        Category expectedCategoryFound = categoryMapper.toModel(expectedCategoryDTO);

        when(categoryRepository.findByName(expectedCategoryDTO.getName())).thenReturn(Optional.of(expectedCategoryFound));

        CategoryDTO foundCategory = categoryService.findByName(expectedCategoryDTO.getName());

        assertThat(foundCategory, is(equalTo(expectedCategoryDTO)));
    }

    @Test
    void whenNotRegisteredCategoryNameIsGivenThenThrowAnException() {
        CategoryDTO expectedCategoryDTO = CategoryDTOBuilder.builder().build().toCategoryDTO();

        when(categoryRepository.findByName(expectedCategoryDTO.getName())).thenReturn(Optional.empty());

        assertThrows(CategoryNotFoundException.class, () -> categoryService.findByName(expectedCategoryDTO.getName()));

    }

    @Test
    void whenListCategoryIsCalledThenReturnAListOfCategories() {
        CategoryDTO expectedCategoryDTO = CategoryDTOBuilder.builder().build().toCategoryDTO();
        Category expectedFoundCategory = categoryMapper.toModel(expectedCategoryDTO);

        when(categoryRepository.findAll()).thenReturn(Collections.singletonList(expectedFoundCategory));

        List<CategoryDTO> foundListCategoriesDTO = categoryService.listAll();

        assertThat(foundListCategoriesDTO, is(not(empty())));
        assertThat(foundListCategoriesDTO.get(0), is(equalTo(expectedCategoryDTO)));
    }

    @Test
    void whenListCategoryIsCalledThenReturnAnEmptyListOfCategories() {
        when(categoryRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<CategoryDTO> foundListCategoriesDTO = categoryService.listAll();

        assertThat(foundListCategoriesDTO, is(empty()));
    }

    @Test
    void whenExclusionIsCalledWithValidIdThenACategoryShouldBeDeleted() throws CategoryNotFoundException {
        CategoryDTO expectedCategoryDTO = CategoryDTOBuilder.builder().build().toCategoryDTO();
        Category expectedDeletedCategory = categoryMapper.toModel(expectedCategoryDTO);

        when(categoryRepository.findById(expectedCategoryDTO.getId())).thenReturn(Optional.of(expectedDeletedCategory));
        doNothing().when(categoryRepository).deleteById(expectedCategoryDTO.getId());

        categoryService.deleteById(expectedCategoryDTO.getId());

        verify(categoryRepository, times(1)).findById(expectedCategoryDTO.getId());
        verify(categoryRepository, times(1)).deleteById(expectedCategoryDTO.getId());
    }

    @Test
    void WhenExclusionIsCalledWithInvalidIdThenThrowAnException() {
        assertThrows(CategoryNotFoundException.class, () -> categoryService.deleteById(INVALID_CATEGORY_ID));
    }

}
