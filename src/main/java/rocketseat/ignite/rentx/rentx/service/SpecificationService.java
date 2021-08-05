package rocketseat.ignite.rentx.rentx.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rocketseat.ignite.rentx.rentx.dto.SpecificationDTO;
import rocketseat.ignite.rentx.rentx.dto.mapper.SpecificationMapper;
import rocketseat.ignite.rentx.rentx.entity.Specification;
import rocketseat.ignite.rentx.rentx.exception.specification.SpecificationAlreadyRegisteredException;
import rocketseat.ignite.rentx.rentx.exception.specification.SpecificationNotFound;
import rocketseat.ignite.rentx.rentx.repository.SpecificationRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SpecificationService {

    private final SpecificationRepository specificationRepository;
    private final SpecificationMapper specificationMapper = SpecificationMapper.INSTANCE;

    public SpecificationDTO create(SpecificationDTO specificationDTO) throws SpecificationAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(specificationDTO.getName());

        specificationDTO.setId(UUID.randomUUID().toString());
        specificationDTO.setCreatedAt(LocalDateTime.now());

        Specification specificationToSave = specificationMapper.toModel(specificationDTO);
        specificationRepository.save(specificationToSave);

        return specificationDTO;
    }

    public List<SpecificationDTO> listAll() {
        List<Specification> all = specificationRepository.findAll();

        return all.stream()
                .map(specificationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public SpecificationDTO findById(String id) throws SpecificationNotFound {
        Specification specification = verifyIfSpecificationExists(id);

        return specificationMapper.toDTO(specification);
    }

    public SpecificationDTO updateById(String id, Optional<SpecificationDTO> specificationDTO) throws SpecificationNotFound, SpecificationAlreadyRegisteredException {
        verifyIfIsAlreadyRegistered(specificationDTO.get().getName());
        Specification specificationToUpdate = verifyIfSpecificationExists(id);

        specificationToUpdate.setName(specificationDTO.get().getName());
        specificationToUpdate.setDescription(specificationDTO.get().getDescription());

        specificationRepository.save(specificationToUpdate);

        return specificationMapper.toDTO(specificationToUpdate);
    }

    public void deleteById(String id) throws SpecificationNotFound {
        verifyIfSpecificationExists(id);

        specificationRepository.deleteById(id);
    }

    private Specification verifyIfSpecificationExists(String id) throws SpecificationNotFound {
        return specificationRepository.findById(id)
                .orElseThrow(() -> new SpecificationNotFound("Specification not found with id: " + id));
    }

    private void verifyIfIsAlreadyRegistered(String name) throws SpecificationAlreadyRegisteredException {
        Optional<Specification> existSpecification = specificationRepository.findByName(name);

        if (existSpecification.isPresent()) {
            throw new SpecificationAlreadyRegisteredException(String.format("Specification with name %s already registered", name));
        }
    }
}
