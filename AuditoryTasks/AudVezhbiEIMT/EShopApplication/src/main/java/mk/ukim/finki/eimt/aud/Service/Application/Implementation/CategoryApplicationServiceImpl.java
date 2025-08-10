package mk.ukim.finki.eimt.aud.Service.Application.Implementation;

import mk.ukim.finki.eimt.aud.Model.DTO.CreateCategoryDTO;
import mk.ukim.finki.eimt.aud.Model.DTO.DisplayCategoryDTO;
import mk.ukim.finki.eimt.aud.Service.Application.CategoryApplicationService;
import mk.ukim.finki.eimt.aud.Service.Domain.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryApplicationServiceImpl implements CategoryApplicationService {

    private final CategoryService categoryService;

    public CategoryApplicationServiceImpl(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public List<DisplayCategoryDTO> findAll() {
        return DisplayCategoryDTO.from(this.categoryService.findAll());
    }

    @Override
    public Optional<DisplayCategoryDTO> findByID(Long ID) {
        return this.categoryService.findByID(ID)
                .map(DisplayCategoryDTO::from);
    }

    @Override
    public Optional<DisplayCategoryDTO> create(CreateCategoryDTO createCategoryDTO) {
        return this.categoryService.create(createCategoryDTO.toCategory())
                .map(DisplayCategoryDTO::from);
    }

    @Override
    public Optional<DisplayCategoryDTO> update(Long ID, CreateCategoryDTO createCategoryDTO) {
        return this.categoryService.update(ID, createCategoryDTO.toCategory())
                .map(DisplayCategoryDTO::from);

    }

    @Override
    public void deleteByID(Long ID) {
        this.categoryService.deleteByID(ID);
    }
}