package space.artexplorer.api.laboratory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class LaboratoryService {
        private final LaboratoryRepository laboratoryRepository;

    @Autowired
    public LaboratoryService(LaboratoryRepository laboratoryRepository) {
        this.laboratoryRepository = laboratoryRepository;
    }

    @Transactional
    public void setLaboratory(Laboratory laboratory) {
        this.laboratoryRepository.save(laboratory);
    }

    public Laboratory getLaboratory(Long laboratoryId) {
        Optional<Laboratory> laboratoryOptional = laboratoryRepository.findById(laboratoryId);
        if (laboratoryOptional.isPresent()) {
            Laboratory laboratory = laboratoryOptional.get();
            return laboratory;
        } else {
            throw new IllegalStateException("Laboratory with ID " + laboratoryId + " doesn't exists");
        }
    }

    public List<Laboratory> getLaboratories() {
        return this.laboratoryRepository.findAll();
    }

    @Transactional
    public void deleteLaboratory(Long laboratoryId) {
        this.laboratoryRepository.deleteById(laboratoryId);
    }

    @Transactional
    public void updateTitle(Long laboratoryId, String title) {
        Optional<Laboratory> optionalLaboratory = this.laboratoryRepository.findById(laboratoryId);
        if (optionalLaboratory.isPresent() && !title.isEmpty()) {
            Laboratory laboratory = optionalLaboratory.get();
            laboratory.setTitle(title);
            this.laboratoryRepository.save(laboratory);
        } else {
            throw new IllegalStateException("Laboratory with ID " + laboratoryId + " doesn't exists or " + title + " is empty");
        }
    }

}
