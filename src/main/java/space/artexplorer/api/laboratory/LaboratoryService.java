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

    public Laboratory getLaboratory(String laboratoryId) {
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
    public void deleteLaboratory(String laboratoryId) {
        this.laboratoryRepository.deleteById(laboratoryId);
    }

    @Transactional
    public void updateLaboratory(
        String laboratoryId,
        String laboratoryTitle,
        String laboratoryDescription,
        Integer laboratoryMinAge,
        Integer laboratoryMaxAge
    ) {
        Optional<Laboratory> optionalLaboratory = this.laboratoryRepository.findById(laboratoryId);
        if (optionalLaboratory.isPresent()) {
            Laboratory laboratory = optionalLaboratory.get();
            if (laboratoryTitle != null) {
                laboratory.setTitle(laboratoryTitle);
                this.laboratoryRepository.save(laboratory);
            }
            if (laboratoryDescription != null) {
                laboratory.setDescription(laboratoryDescription);
                this.laboratoryRepository.save(laboratory);
            }
            if (laboratoryMinAge != null) {
                laboratory.setMinAge(laboratoryMinAge);
                this.laboratoryRepository.save(laboratory);
            }
            if (laboratoryMaxAge != null) {
                laboratory.setMaxAge(laboratoryMaxAge);
                this.laboratoryRepository.save(laboratory);
            }
        } else {
            throw new IllegalStateException("Laboratory with ID " + laboratoryId + " doesn't exists");
        }
    }

}