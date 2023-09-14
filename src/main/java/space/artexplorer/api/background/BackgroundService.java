package space.artexplorer.api.background;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.artexplorer.api.laboratory.Laboratory;
import space.artexplorer.api.laboratory.LaboratoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BackgroundService {
    private final BackgroundRepository backgroundRepository;
    private final LaboratoryRepository laboratoryRepository;

    @Autowired
    public BackgroundService(BackgroundRepository backgroundRepository, LaboratoryRepository laboratoryRepository) {
        this.backgroundRepository = backgroundRepository;
        this.laboratoryRepository = laboratoryRepository;
    }

    public List<Background> getBackgrounds() {
        return this.backgroundRepository.findAll();
    }

    @Transactional
    public void setBackground(Background background) {
        this.backgroundRepository.save(background);
    }

    public Background getBackground(Long backgroundId) {
        Optional<Background> backgroundOptional = backgroundRepository.findById(backgroundId);
        if (backgroundOptional.isPresent()) {
            return backgroundOptional.get();
        }
        throw new IllegalStateException("Background with ID " + backgroundId + " doesn't exists");
    }

    @Transactional
    public void deleteBackground(Long backgroundId) {
        Optional<Background> backgroundOptional = backgroundRepository.findById(backgroundId);
        if (backgroundOptional.isPresent()) {
            Background background = backgroundOptional.get();
            this.backgroundRepository.delete(background);
        } else {
            throw new IllegalStateException("Background with ID " + backgroundId + " doesn't exists");
        }
    }

    @Transactional
    public void updateBackground(Long backgroundId, String backgroundUrl, String backgroundTextColor, String backgroundTitleTextColor) {
        Optional<Background> backgroundOptional = this.backgroundRepository.findById(backgroundId);
        if (backgroundOptional.isPresent()) {
            Background background = backgroundOptional.get();
            if (backgroundUrl != null) {
                background.setUrl(backgroundUrl);
                this.backgroundRepository.save(background);
            }
            if (backgroundTextColor != null) {
                background.setTextColor(backgroundTextColor);
                this.backgroundRepository.save(background);
            }
            if (backgroundTitleTextColor != null) {
                background.setTitleTextColor(backgroundTitleTextColor);
                this.backgroundRepository.save(background);
            }
        } else {
            throw new IllegalStateException("Background with ID " + backgroundId + " doesn't exists");
        }
    }

    @Transactional
    public void setLaboratory(Long backgroundId, String laboratoryId) {
        Optional<Background> backgroundOptional = this.backgroundRepository.findById(backgroundId);
        Optional<Laboratory> laboratoryOptional = this.laboratoryRepository.findById(laboratoryId);
        if (backgroundOptional.isPresent() && laboratoryOptional.isPresent()) {
            Background background = backgroundOptional.get();
            Laboratory laboratory = laboratoryOptional.get();
            background.setLaboratory(laboratory);
            this.backgroundRepository.save(background);
        } else {
            throw new IllegalStateException("Background with ID " + backgroundId + " or laboratory with ID " + laboratoryId + " doesn't exists");
        }
    }
}
