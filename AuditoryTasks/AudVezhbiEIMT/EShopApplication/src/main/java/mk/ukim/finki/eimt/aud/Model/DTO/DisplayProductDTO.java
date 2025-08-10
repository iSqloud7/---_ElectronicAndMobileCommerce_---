package mk.ukim.finki.eimt.aud.Model.DTO;

import mk.ukim.finki.eimt.aud.Model.Entity.Category;
import mk.ukim.finki.eimt.aud.Model.Entity.Manufacturer;
import mk.ukim.finki.eimt.aud.Model.Entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public record DisplayProductDTO(

        Long ID,
        String name,
        Double price,
        Integer quantity,
        Long categoryID,
        Long manufacturerID
) {
    public static DisplayProductDTO from(Product product) {
        return new DisplayProductDTO(
                product.getID(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory().getID(),
                product.getManufacturer().getID()
        );
    }

    public static List<DisplayProductDTO> from(List<Product> products) {
        return products.stream()
                .map(DisplayProductDTO::from)
                .collect(Collectors.toList());
    }

    public Product toProduct(Category category, Manufacturer manufacturer) {
        return new Product(name, price, quantity, category, manufacturer);
    }
}