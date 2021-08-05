package rocketseat.ignite.rentx.rentx.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import rocketseat.ignite.rentx.rentx.dto.SpecificationDTO;
import rocketseat.ignite.rentx.rentx.exception.specification.SpecificationAlreadyRegisteredException;
import rocketseat.ignite.rentx.rentx.exception.specification.SpecificationNotFound;
import rocketseat.ignite.rentx.rentx.service.SpecificationService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/specifications")
public class SpecificationController {

    private final SpecificationService specificationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SpecificationDTO create(@RequestBody @Valid SpecificationDTO specificationDTO) throws SpecificationAlreadyRegisteredException {
        return specificationService.create(specificationDTO);
    }

    @GetMapping
    public List<SpecificationDTO> listAll() {
        return specificationService.listAll();
    }

    @GetMapping("/{id}")
    public SpecificationDTO findById(@PathVariable String id) throws SpecificationNotFound {
        return specificationService.findById(id);
    }

    @PatchMapping("/{id}")
    public SpecificationDTO updateById(@PathVariable String id, @RequestBody Optional<SpecificationDTO> specificationDTO) throws SpecificationNotFound, SpecificationAlreadyRegisteredException {
        return specificationService.updateById(id, specificationDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable String id) throws SpecificationNotFound {
        specificationService.deleteById(id);
    }

}
