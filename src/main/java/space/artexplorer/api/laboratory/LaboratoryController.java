package space.artexplorer.api.laboratory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/v1/laboratory")

public class LaboratoryController {
    private final LaboratoryService laboratoryService;

    @Autowired
    public LaboratoryController(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    @GetMapping(path = "all")
    List<Laboratory> getLaboratories() {
        return laboratoryService.getLaboratories();
    }

    @PostMapping
    public void setLaboratory(@RequestBody Laboratory laboratory) {
        this.laboratoryService.setLaboratory(laboratory);
    }

    @DeleteMapping(path="{laboratoryId}")
    public void deleteLaboratory(@PathVariable Long laboratoryId) {
        this.laboratoryService.deleteLaboratory(laboratoryId);
    }
}