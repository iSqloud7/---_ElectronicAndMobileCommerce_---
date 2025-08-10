package mk.ukim.finki.eimt.aud.Model.DTO;

import mk.ukim.finki.eimt.aud.Model.Entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public record CreateCategoryDTO(

        String name,
        String description
) {
    public static CreateCategoryDTO from(Category category) {
        return new CreateCategoryDTO(
                category.getName(),
                category.getDescription()
        );
    }

    public static List<CreateCategoryDTO> from(List<Category> categories) {
        return categories.stream()
                .map(CreateCategoryDTO::from)
                .collect(Collectors.toList());
    }

    public Category toCategory() {
        return new Category(name, description);
    }
}