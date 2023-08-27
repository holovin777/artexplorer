package space.artexplorer.api.photo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/v1/photo")

public class PhotoController {
    private final PhotoService photoService;
    @Autowired
    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    @PostMapping
    public void setPhoto(@RequestBody Photo photo) {
        this.photoService.setPhoto(photo);
    }

    @PutMapping(path = "{photoId}/laboratory")
    public void setLaboratory(@PathVariable String photoId, @RequestParam String laboratoryId) {
        this.photoService.setLaboratory(laboratoryId, photoId);
    }

    @GetMapping(path = "all")
    public List<Photo> getPhotos() {
        return this.photoService.getPhotos();
    }

    @GetMapping(path = "laboratory/{laboratoryId}")
    public List<Photo> findPhotosByLaboratoryId(@PathVariable String laboratoryId) {
        return this.photoService.findPhotosByLaboratoryId(laboratoryId);
    }

    @PutMapping(path = "{photoId}/update")
    public void updatePhoto(
            @PathVariable String photoId,
            @RequestParam(required = false) String photoUrl,
            @RequestParam(required = false) Integer photoSequence
    ) {
        this.photoService.updatePhoto(photoId, photoUrl, photoSequence);
    }

}