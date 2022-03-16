package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.payload.CategoryDto;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;


    @PostMapping
    public Result addCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.addCategory(categoryDto);
    }

    @GetMapping
    public Result getCategories() {
        return categoryService.getCategories();
    }

    @GetMapping("/{id}")
    public Result getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryById(id);
    }

    @PutMapping("/{id}")
    public Result editCategoryById(@PathVariable Integer id, @RequestBody CategoryDto categoryDto) {
        return categoryService.editCategoryById(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    public Result deleteCategoryById(@PathVariable Integer id) {
        return categoryService.deleteCategoryById(id);
    }
}
