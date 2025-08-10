package mk.ukim.finki.eimt.lab.Model.DTO.AuthorDTO;

import mk.ukim.finki.eimt.lab.Model.Entities.Author;
import mk.ukim.finki.eimt.lab.Model.Entities.Country;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayAuthorDTO(
        Long authorID,
        String name,
        String surname,
        Long countryID
) {
    public static DisplayAuthorDTO fromAuthor(Author author) {
        return new DisplayAuthorDTO(
                author.getID(),
                author.getName(),
                author.getSurname(),
                author.getCountry().getID()
        );
    }

    public static List<DisplayAuthorDTO> fromAuthors(List<Author> authors) {
        return authors.stream()
                .map(DisplayAuthorDTO::fromAuthor)
                .collect(Collectors.toList());
    }

    public Author toAuthor(Country country) {
        return new Author(authorID, name, surname, country);
    }
}
