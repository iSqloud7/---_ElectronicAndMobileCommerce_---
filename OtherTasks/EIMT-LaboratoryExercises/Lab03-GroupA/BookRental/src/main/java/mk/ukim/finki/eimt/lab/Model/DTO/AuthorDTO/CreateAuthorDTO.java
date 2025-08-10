package mk.ukim.finki.eimt.lab.Model.DTO.AuthorDTO;

import mk.ukim.finki.eimt.lab.Model.Entities.Author;
import mk.ukim.finki.eimt.lab.Model.Entities.Country;

import java.util.List;
import java.util.stream.Collectors;

public record CreateAuthorDTO(
        String name,
        String surname,
        Long countryID
) {
    public static CreateAuthorDTO fromAuthor(Author author) {
        return new CreateAuthorDTO(
                author.getName(),
                author.getSurname(),
                author.getCountry().getID()
        );
    }

    public static List<CreateAuthorDTO> fromAuthors(List<Author> authors) {
        return authors.stream()
                .map(CreateAuthorDTO::fromAuthor)
                .collect(Collectors.toList());
    }

    public Author toAuthor(Country country) {
        return new Author(name, surname, country);
    }
}
