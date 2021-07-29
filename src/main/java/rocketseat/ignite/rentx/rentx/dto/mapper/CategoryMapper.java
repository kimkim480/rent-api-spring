package rocketseat.ignite.rentx.rentx.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import rocketseat.ignite.rentx.rentx.dto.CategoryDTO;
import rocketseat.ignite.rentx.rentx.entity.Category;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    Category  toModel(CategoryDTO categoryDTO);

    CategoryDTO toDTO(Category category);
}
