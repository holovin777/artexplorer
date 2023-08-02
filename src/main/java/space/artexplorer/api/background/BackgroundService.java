package space.artexplorer.api.background;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.artexplorer.api.background.Background;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BackgroundService {
    private final BackgroundRepository backgroundRepository;

    @Autowired
    public BackgroundService(BackgroundRepository backgroundRepository) {
        this.backgroundRepository = backgroundRepository;
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

}
