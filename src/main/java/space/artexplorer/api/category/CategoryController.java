package space.artexplorer.api.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public void deleteCategory(@PathVariable Long categoryId) {
        this.categoryService.deleteCategory(categoryId);
    }

    @GetMapping(path = "{categoryId}")
    public Category getCategory(@PathVariable Long categoryId) {
        return this.categoryService.getCategory(categoryId);
    }

    @PostMapping(path = "{categoryId}/addLaboratory")
    public void addLaboratory(@PathVariable Long categoryId, @RequestParam Long laboratoryId) {
        this.categoryService.addLaboratory(categoryId, laboratoryId);
    }

    @DeleteMapping(path = "{categoryId}/deleteLaboratory")
    public void deleteLaboratory(@PathVariable Long categoryId, @RequestParam Long laboratoryId) {
        this.categoryService.deleteLaboratory(categoryId, laboratoryId);
    }

}
