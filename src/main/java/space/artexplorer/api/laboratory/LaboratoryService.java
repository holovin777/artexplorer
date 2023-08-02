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

        public List<Laboratory> getLaboratories() {
            return this.laboratoryRepository.findAll();
        }

        @Transactional
        public void setLaboratory(Laboratory laboratory) {
            this.laboratoryRepository.save(laboratory);
        }

        public Laboratory getLaboratory(Long laboratoryId) {
            Optional<Laboratory> laboratoryOptional = laboratoryRepository.findById(laboratoryId);
            if (laboratoryOptional.isPresent()) {
                return laboratoryOptional.get();
            }
            throw new IllegalStateException("Laboratory with ID " + laboratoryId + " doesn't exists");
        }

        @Transactional
        public void deleteLaboratory(Long laboratoryId) {
            Optional<Laboratory> laboratoryOptional = laboratoryRepository.findById(laboratoryId);
            if (laboratoryOptional.isPresent()) {
                Laboratory laboratory = laboratoryOptional.get();
                this.laboratoryRepository.delete(laboratory);
            } else {
                throw new IllegalStateException("Laboratory with ID " + laboratoryId + " doesn't exists");
            }
        }

}
