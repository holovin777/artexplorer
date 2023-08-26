package space.artexplorer.api.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.artexplorer.api.laboratory.LaboratoryService;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final LaboratoryService laboratoryService;

    @Autowired
    public CategoryController(CategoryService categoryService, LaboratoryService laboratoryService) {
        this.categoryService = categoryService;
        this.laboratoryService = laboratoryService;
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

    @PutMapping(path = "{categoryId}/laboratory")
    public void addLaboratory(@PathVariable String categoryId, @RequestParam String laboratoryId) {
        this.categoryService.addLaboratory(categoryId, laboratoryId);
    }

    @DeleteMapping(path = "{categoryId}/laboratory")
    public void deleteLaboratory(@PathVariable String categoryId, @RequestParam String laboratoryId) {
        this.categoryService.deleteLaboratory(categoryId, laboratoryId);
    }

}
