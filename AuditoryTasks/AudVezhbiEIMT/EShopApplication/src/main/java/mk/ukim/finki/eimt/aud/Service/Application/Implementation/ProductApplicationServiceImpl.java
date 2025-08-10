package mk.ukim.finki.eimt.aud.Service.Application.Implementation;

import mk.ukim.finki.eimt.aud.Model.DTO.CreateProductDTO;
import mk.ukim.finki.eimt.aud.Model.DTO.DisplayProductDTO;
import mk.ukim.finki.eimt.aud.Model.Entity.Category;
import mk.ukim.finki.eimt.aud.Model.Entity.Manufacturer;
import mk.ukim.finki.eimt.aud.Service.Application.ProductApplicationService;
import mk.ukim.finki.eimt.aud.Service.Domain.CategoryService;
import mk.ukim.finki.eimt.aud.Service.Domain.ManufacturerService;
import mk.ukim.finki.eimt.aud.Service.Domain.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductApplicationServiceImpl implements ProductApplicationService {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ManufacturerService manufacturerService;

    public ProductApplicationServiceImpl(ProductService productService, CategoryService categoryService, ManufacturerService manufacturerService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.manufacturerService = manufacturerService;
    }

    @Override
    public List<DisplayProductDTO> findAll() {
        return this.productService.findAll().stream()
                .map(DisplayProductDTO::from)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DisplayProductDTO> findByID(Long ID) {
        return this.productService.findByID(ID)
                .map(DisplayProductDTO::from);
    }

    @Override
    public Optional<DisplayProductDTO> create(CreateProductDTO createProductDTO) {
        Optional<Category> category_obj = this.categoryService.findByID(createProductDTO.categoryID());
        Optional<Manufacturer> manufacturer_obj = this.manufacturerService.findByID(createProductDTO.manufacturerID());

        if (category_obj.isPresent() && manufacturer_obj.isPresent()) {

            return this.productService.create(createProductDTO.toProduct(category_obj.get(), manufacturer_obj.get()))
                    .map(DisplayProductDTO::from);
        }

        return Optional.empty();
    }

    @Override
    public Optional<DisplayProductDTO> update(Long ID, CreateProductDTO createProductDTO) {
        Optional<Category> category_obj = this.categoryService.findByID(createProductDTO.categoryID());
        Optional<Manufacturer> manufacturer_obj = this.manufacturerService.findByID(createProductDTO.manufacturerID());

        return this.productService.update(
                ID,
                createProductDTO.toProduct(
                        category_obj.orElse(null),
                        manufacturer_obj.orElse(null)
                )
        ).map(DisplayProductDTO::from);
    }

    @Override
    public void deleteByID(Long ID) {
        this.productService.deleteByID(ID);
    }
}