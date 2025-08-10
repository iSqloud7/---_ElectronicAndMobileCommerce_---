package mk.ukim.finki.eimt.aud.Service.Domain;

import mk.ukim.finki.eimt.aud.Model.Entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findByID(Long ID);

    Optional<Product> create(Product product);

    Optional<Product> update(Long ID, Product product);

    void deleteByID(Long ID);
}