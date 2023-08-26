package space.artexplorer.api.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, String> {
    Optional<Photo> findByUrl(String url);
    List<Photo> findPhotosByLaboratoryId(String laboratoryId);
}