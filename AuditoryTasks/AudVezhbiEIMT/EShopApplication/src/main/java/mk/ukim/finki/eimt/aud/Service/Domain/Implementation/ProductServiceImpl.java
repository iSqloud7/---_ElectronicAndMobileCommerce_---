package mk.ukim.finki.eimt.aud.Service.Domain.Implementation;

import mk.ukim.finki.eimt.aud.Model.Entity.Product;
import mk.ukim.finki.eimt.aud.Repository.ProductRepository;
import mk.ukim.finki.eimt.aud.Service.Domain.CategoryService;
import mk.ukim.finki.eimt.aud.Service.Domain.ManufacturerService;
import mk.ukim.finki.eimt.aud.Service.Domain.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    public ProductServiceImpl(ProductRepository productRepository, CategoryService categoryService, ManufacturerService manufacturerService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findByID(Long ID) {
        return this.productRepository.findById(ID);
    }

    @Override
    public Optional<Product> create(Product product) {

        if (product.getCategory() != null && this.categoryService.findByID(product.getCategory().getID()).isPresent() &&
                product.getManufacturer() != null && this.manufacturerService.findByID(product.getManufacturer().getID()).isPresent()) {

            return Optional.of(this.productRepository.save(
                    new Product(
                            product.getName(),
                            product.getPrice(),
                            product.getQuantity(),
                            this.categoryService.findByID(product.getCategory().getID()).get(),
                            this.manufacturerService.findByID(product.getManufacturer().getID()).get())
            ));
        }

        return Optional.empty();
    }

    @Override
    public Optional<Product> update(Long ID, Product product) {
        return this.productRepository.findById(ID)
                .map(existingProduct_obj -> {
                    if (existingProduct_obj.getName() != null) {
                        existingProduct_obj.setName(product.getName());
                    }
                    if (existingProduct_obj.getPrice() != null) {
                        existingProduct_obj.setPrice(product.getPrice());
                    }
                    if (existingProduct_obj.getQuantity() != null) {
                        existingProduct_obj.setQuantity(product.getQuantity());
                    }
                    if (existingProduct_obj.getCategory() != null &&
                            this.categoryService.findByID(product.getCategory().getID()).isPresent()) {
                        existingProduct_obj.setCategory(this.categoryService.findByID(product.getCategory().getID()).get());
                    }
                    if (existingProduct_obj.getManufacturer() != null &&
                            this.manufacturerService.findByID(product.getManufacturer().getID()).isPresent()) {
                        existingProduct_obj.setManufacturer(this.manufacturerService.findByID(product.getManufacturer().getID()).get());
                    }

                    return this.productRepository.save(existingProduct_obj);
                });
    }

    @Override
    public void deleteByID(Long ID) {
        this.productRepository.deleteById(ID);
    }
}