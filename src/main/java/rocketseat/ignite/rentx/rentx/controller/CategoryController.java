package rocketseat.ignite.rentx.rentx.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rocketseat.ignite.rentx.rentx.dto.CategoryDTO;
import rocketseat.ignite.rentx.rentx.exception.CategoryAlreadyRegisteredException;
import rocketseat.ignite.rentx.rentx.exception.CategoryNotFoundException;
import rocketseat.ignite.rentx.rentx.helper.CSVHelper;
import rocketseat.ignite.rentx.rentx.service.CategoryService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private CategoryService categoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO create(@RequestBody @Valid CategoryDTO categoryDTO) throws CategoryAlreadyRegisteredException {
        return categoryService.create(categoryDTO);
    }

    @GetMapping
    public List<CategoryDTO> listAll() {
        return categoryService.listAll();
    }

    @GetMapping("/{id}")
    public CategoryDTO findById(@PathVariable String id) throws CategoryNotFoundException {
        return categoryService.findById(id);
    }

    @PatchMapping("/{id}")
    public CategoryDTO updateById(@PathVariable String id, @RequestBody @Valid Optional<CategoryDTO> categoryDTO) throws CategoryNotFoundException, CategoryAlreadyRegisteredException {
        return categoryService.updateById(id, categoryDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable String id) throws CategoryNotFoundException {
        categoryService.deleteById(id);
    }

    @PostMapping("/import")
    public void importCSV(@RequestParam("file") MultipartFile file) {
        if (CSVHelper.hasCSVFormat(file)) {
            categoryService.importCSV(file);
        }
    }

}
