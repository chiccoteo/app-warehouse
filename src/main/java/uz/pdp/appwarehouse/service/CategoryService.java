package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Category;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.repo.CategoryRepo;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;


    public Result addCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalParentCategory = categoryRepo.findById(categoryDto.getParentCategoryId());
            if (optionalParentCategory.isEmpty())
                return new Result("Such category not found", false);
            category.setParentCategory(optionalParentCategory.get());
        }
        categoryRepo.save(category);
        return new Result("Successfully saved", true);
    }

    public Result getCategories() {
        return new Result("Successfully", true, categoryRepo.findAll());
    }

    public Result getCategoryById(Integer id) {
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        if (optionalCategory.isEmpty())
            return new Result("Such a category not found", false);
        return new Result("Successfully", true, optionalCategory.get());
    }

    public Result editCategoryById(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        if (optionalCategory.isEmpty())
            return new Result("Such a category not found", false);
        Category category = optionalCategory.get();
        if (categoryDto.getParentCategoryId() != null) {
            Optional<Category> optionalParentCategory = categoryRepo.findById(categoryDto.getParentCategoryId());
            if (optionalParentCategory.isEmpty()) {
                return new Result("Such a category not found", false);
            }
            category.setParentCategory(optionalParentCategory.get());
        }
        category.setName(categoryDto.getName());
        categoryRepo.save(category);
        return new Result("Successfully edited", true);
    }

    public Result deleteCategoryById(Integer id) {
        Optional<Category> optionalCategory = categoryRepo.findById(id);
        if (optionalCategory.isEmpty())
            return new Result("Such a category not found", false);
        categoryRepo.deleteById(id);
        return new Result("Successfully deleted", true);
    }
}
