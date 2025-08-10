package mk.ukim.finki.eimt.aud.Service.Domain;

import mk.ukim.finki.eimt.aud.Model.Entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    Optional<Category> findByID(Long ID);

    Optional<Category> create(Category category);

    Optional<Category> update(Long ID, Category category);

    void deleteByID(Long ID);
}