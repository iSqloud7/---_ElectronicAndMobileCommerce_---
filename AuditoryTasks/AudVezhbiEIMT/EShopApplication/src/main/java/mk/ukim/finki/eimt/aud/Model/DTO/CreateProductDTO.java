package mk.ukim.finki.eimt.aud.Model.DTO;

import mk.ukim.finki.eimt.aud.Model.Entity.Category;
import mk.ukim.finki.eimt.aud.Model.Entity.Manufacturer;
import mk.ukim.finki.eimt.aud.Model.Entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public record CreateProductDTO(

        String name,
        Double price,
        Integer quantity,
        Long categoryID,
        Long manufacturerID
) {
    public static CreateProductDTO from(Product product) {
        return new CreateProductDTO(
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory().getID(),
                product.getManufacturer().getID()
        );
    }

    public static List<CreateProductDTO> from(List<Product> products) {
        return products.stream()
                .map(CreateProductDTO::from)
                .collect(Collectors.toList());
    }

    public Product toProduct(Category category, Manufacturer manufacturer) {
        return new Product(name, price, quantity, category, manufacturer);
    }
}