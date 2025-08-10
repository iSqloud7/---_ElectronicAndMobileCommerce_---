package mk.ukim.finki.eimt.aud.Model.DTO;

import mk.ukim.finki.eimt.aud.Model.Entity.Category;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayCategoryDTO(

        Long ID,
        String name,
        String description
) {
    public static DisplayCategoryDTO from(Category category) {
        return new DisplayCategoryDTO(
                category.getID(),
                category.getName(),
                category.getDescription()
        );
    }

    public static List<DisplayCategoryDTO> from(List<Category> categories) {
        return categories.stream()
                .map(DisplayCategoryDTO::from)
                .collect(Collectors.toList());
    }

    public Category toCategory() {
        return new Category(name, description);
    }
}