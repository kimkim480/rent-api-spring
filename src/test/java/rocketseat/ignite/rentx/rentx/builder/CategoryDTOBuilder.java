package rocketseat.ignite.rentx.rentx.builder;

import lombok.Builder;
import rocketseat.ignite.rentx.rentx.dto.CategoryDTO;

import java.time.LocalDateTime;

@Builder
public class CategoryDTOBuilder {

    @Builder.Default
    private String id = "9470f0e4-d4c9-4e93-9c8d-18fbc62d2a16";

    @Builder.Default
    private String name = "Category test";

    @Builder.Default
    private String description = "Description test";

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    public CategoryDTO toCategoryDTO() {
        return new CategoryDTO(id, name, description, createdAt);
    }
}
