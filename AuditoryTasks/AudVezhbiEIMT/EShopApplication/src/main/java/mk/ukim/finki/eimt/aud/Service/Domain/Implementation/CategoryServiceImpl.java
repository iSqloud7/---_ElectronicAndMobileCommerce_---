package mk.ukim.finki.eimt.aud.Service.Domain.Implementation;

import mk.ukim.finki.eimt.aud.Model.Entity.Category;
import mk.ukim.finki.eimt.aud.Repository.CategoryRepository;
import mk.ukim.finki.eimt.aud.Service.Domain.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findByID(Long ID) {
        return this.categoryRepository.findById(ID);
    }

    @Override
    public Optional<Category> create(Category category) {
        return Optional.of(this.categoryRepository.save(category));
    }

    @Override
    public Optional<Category> update(Long ID, Category category) {
        return this.categoryRepository.findById(ID)
                .map(existingCategory_obj -> {
                    if (category.getName() != null) {
                        existingCategory_obj.setName(category.getName());
                    }
                    if (category.getDescription() != null) {
                        existingCategory_obj.setDescription(category.getDescription());
                    }

                    return this.categoryRepository.save(existingCategory_obj);
                });
    }

    @Override
    public void deleteByID(Long ID) {
        this.categoryRepository.deleteById(ID);
    }
}