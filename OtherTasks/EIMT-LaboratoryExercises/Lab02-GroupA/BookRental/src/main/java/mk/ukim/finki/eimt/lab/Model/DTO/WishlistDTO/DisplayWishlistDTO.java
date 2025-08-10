package mk.ukim.finki.eimt.lab.Model.DTO.WishlistDTO;

import mk.ukim.finki.eimt.lab.Model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayWishlistDTO(
        List<String> bookNames
) {
    public static DisplayWishlistDTO fromWishlistToDisplayWishlistDTO(List<Book> books) {
        List<String> bookNames = new ArrayList<>();

        for (Book book : books) {
            bookNames.add(book.getName());
        }

        return new DisplayWishlistDTO(bookNames);
    }
}
