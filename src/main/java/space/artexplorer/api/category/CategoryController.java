package space.artexplorer.api.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(path = "all")
    public List<Category> getCategories() {
        return categoryService.getCategories();
    }

    @PostMapping
    public void setCategory(@RequestBody Category category) {
        this.categoryService.setCategory(category);
    }

    @DeleteMapping(path="{categoryId}")
    public void deleteCategory(@PathVariable String categoryId) {
        this.categoryService.deleteCategory(categoryId);
    }

    @GetMapping(path = "{categoryId}")
    public Category getCategory(@PathVariable String categoryId) {
        return this.categoryService.getCategory(categoryId);
    }

    @PutMapping(path = "{categoryId}/update")
    public void updateCategory(
        @PathVariable String categoryId,
        @RequestParam(required = false) String categoryName,
        @RequestParam(required = false) String categoryNameTextColor
        ) {
        this.categoryService.updateCategory(
                categoryId, categoryName, categoryNameTextColor
        );
    }

    @PutMapping(path = "{categoryId}/laboratory")
    public void addLaboratory(@PathVariable String categoryId, @RequestParam String laboratoryId) {
        this.categoryService.addLaboratory(categoryId, laboratoryId);
    }

    @DeleteMapping(path = "{categoryId}/laboratory")
    public void deleteLaboratory(@PathVariable String categoryId, @RequestParam String laboratoryId) {
        this.categoryService.deleteLaboratory(categoryId, laboratoryId);
    }

}