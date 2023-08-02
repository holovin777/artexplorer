package space.artexplorer.api.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    @Transactional
    public void setCategory(Category category) {
        this.categoryRepository.save(category);
    }

    public Category getCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        }
        throw new IllegalStateException("Category with ID " + categoryId + " doesn't exists");
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            this.categoryRepository.delete(category);
        } else {
            throw new IllegalStateException("Category with ID " + categoryId + " doesn't exists");
        }
    }

}