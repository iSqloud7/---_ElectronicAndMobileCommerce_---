package mk.ukim.finki.eimt.lab.Model.DTO.BookDTO;

import mk.ukim.finki.eimt.lab.Model.Entities.Author;
import mk.ukim.finki.eimt.lab.Model.Entities.Book;
import mk.ukim.finki.eimt.lab.Model.Enumerations.BookCategory;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayBookDTO(
        Long bookID,
        String name,
        BookCategory category,
        Long authorID,
        Integer availableCopies
) {
    public static DisplayBookDTO fromBook(Book book) {
        return new DisplayBookDTO(
                book.getID(),
                book.getName(),
                book.getCategory(),
                book.getAuthor().getID(),
                book.getAvailableCopies()
        );
    }

    public static List<DisplayBookDTO> fromBooks(List<Book> books) {
        return books.stream()
                .map(DisplayBookDTO::fromBook)
                .collect(Collectors.toList());
    }

    public Book toBook(Author author) {
        return new Book(bookID, name, category, author, availableCopies);
    }
}
