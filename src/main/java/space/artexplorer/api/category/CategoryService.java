package space.artexplorer.api.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.artexplorer.api.laboratory.Laboratory;
import space.artexplorer.api.laboratory.LaboratoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final LaboratoryRepository laboratoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, LaboratoryRepository laboratoryRepository) {
        this.categoryRepository = categoryRepository;
        this.laboratoryRepository = laboratoryRepository;
    }


    public List<Category> getCategories() {
        return this.categoryRepository.findAll();
    }

    @Transactional
    public void setCategory(Category category) {
        this.categoryRepository.save(category);
    }

    public Category getCategory(String categoryId) {
        Optional<Category> categoryOptional = this.categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            return categoryOptional.get();
        }
        throw new IllegalStateException("Category with ID " + categoryId + " doesn't exists");
    }

    @Transactional
    public void deleteCategory(String categoryId) {
        Optional<Category> categoryOptional = this.categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            this.categoryRepository.delete(category);
        } else {
            throw new IllegalStateException("Category with ID " + categoryId + " doesn't exists");
        }
    }

    @Transactional
    public void addLaboratory(String categoryId, String laboratoryId) {
        Optional<Category> categoryOptional = this.categoryRepository.findById(categoryId);
        Optional<Laboratory> laboratoryOptional = this.laboratoryRepository.findById(laboratoryId);
        if (categoryOptional.isPresent() && laboratoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            Laboratory laboratory = laboratoryOptional.get();
            category.addLaboratory(laboratory);
            this.categoryRepository.save(category);
        } else {
            throw new IllegalStateException("Category with ID " + categoryId + " or laboratory with ID " + laboratoryId + " doesn't exists");
        }
    }

    @Transactional
    public void deleteLaboratory(String categoryId, String laboratoryId) {
        Optional<Category> categoryOptional = this.categoryRepository.findById(categoryId);
        Optional<Laboratory> laboratoryOptional = this.laboratoryRepository.findById(laboratoryId);
        if (categoryOptional.isPresent() && laboratoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            Laboratory laboratory = laboratoryOptional.get();
            category.deleteLaboratory(laboratory);
            this.categoryRepository.save(category);
        } else {
            throw new IllegalStateException("Category with ID " + categoryId + " or laboratory with ID " + laboratoryId + " doesn't exists");
        }
    }

}