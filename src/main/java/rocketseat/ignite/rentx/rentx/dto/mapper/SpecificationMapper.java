package rocketseat.ignite.rentx.rentx.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import rocketseat.ignite.rentx.rentx.dto.SpecificationDTO;
import rocketseat.ignite.rentx.rentx.entity.Specification;

@Mapper
public interface SpecificationMapper {
    SpecificationMapper INSTANCE = Mappers.getMapper(SpecificationMapper.class);

    Specification toModel(SpecificationDTO categoryDTO);

    SpecificationDTO toDTO(Specification category);
}
