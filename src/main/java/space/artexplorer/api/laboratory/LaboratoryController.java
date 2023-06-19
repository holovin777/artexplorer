package space.artexplorer.api.laboratory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import space.artexplorer.api.photo.PhotoService;

import java.util.List;


@RestController
@RequestMapping("api/v1/laboratory")

public class LaboratoryController {
    private final LaboratoryService laboratoryService;
    private final PhotoService photoService;
    @Autowired
    public LaboratoryController(LaboratoryService laboratoryService, PhotoService photoService) {
        this.laboratoryService = laboratoryService;
        this.photoService = photoService;
    }
    @GetMapping(name = "all")
    List<Laboratory> getLaboratories() {
        return laboratoryService.getLaboratories();
    }

    @PostMapping
    public void setLaboratory(@RequestBody Laboratory laboratory) {
        this.laboratoryService.setLaboratory(laboratory);
    }


}