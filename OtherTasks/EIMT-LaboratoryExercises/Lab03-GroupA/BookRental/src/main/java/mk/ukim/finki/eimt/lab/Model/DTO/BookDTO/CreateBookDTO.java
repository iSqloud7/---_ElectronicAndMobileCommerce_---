package mk.ukim.finki.eimt.lab.Model.DTO.BookDTO;

import mk.ukim.finki.eimt.lab.Model.Entities.Author;
import mk.ukim.finki.eimt.lab.Model.Entities.Book;
import mk.ukim.finki.eimt.lab.Model.Enumerations.BookCategory;

import java.util.List;
import java.util.stream.Collectors;

public record CreateBookDTO(
        String name,
        BookCategory category,
        Long authorID,
        Integer availableCopies
) {
    public static CreateBookDTO fromBook(Book book) {
        return new CreateBookDTO(
                book.getName(),
                book.getCategory(),
                book.getAuthor().getID(),
                book.getAvailableCopies()
        );
    }

    public static List<CreateBookDTO> fromBooks(List<Book> books) {
        return books.stream()
                .map(CreateBookDTO::fromBook)
                .collect(Collectors.toList());
    }

    public Book toBook(Author author) {
        return new Book(name, category, author, availableCopies);
    }
}
