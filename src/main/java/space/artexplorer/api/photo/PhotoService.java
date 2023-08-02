package space.artexplorer.api.photo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.artexplorer.api.laboratory.Laboratory;
import space.artexplorer.api.laboratory.LaboratoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PhotoService {
        private final PhotoRepository photoRepository;
        private final LaboratoryRepository laboratoryRepository;
        @Autowired
        public PhotoService(PhotoRepository photoRepository, LaboratoryRepository laboratoryRepository) {
            this.photoRepository = photoRepository;
            this.laboratoryRepository = laboratoryRepository;
        }

        @Transactional
        public void setLaboratory(Long laboratoryId, String photoUrl) {
            Optional<Laboratory> laboratoryOptional = this.laboratoryRepository.findById(laboratoryId);
            Optional<Photo> photoOptional = this.photoRepository.findByUrl(photoUrl);
            if (laboratoryOptional.isPresent() && photoOptional.isPresent()) {
                Photo photo = photoOptional.get();
                photo.setUrl(photoUrl);

                Laboratory laboratory = laboratoryOptional.get();
                photo.setLaboratory(laboratory);

                this.photoRepository.save(photo);
            }
        }

        @Transactional
        public void setPhoto(Photo photo) {
                photoRepository.save(photo);
        }

        public List<Photo> getPhotos() {
            return this.photoRepository.findAll();
        }

        public List<Photo> findPhotosByLaboratoryId(Long laboratoryId) {
            return this.photoRepository.findPhotosByLaboratoryId(laboratoryId);
        }

}
