package space.artexplorer.api.laboratory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("api/v1/laboratory")

public class LaboratoryController {
    private final LaboratoryService laboratoryService;

    @Autowired
    public LaboratoryController(LaboratoryService laboratoryService) {
        this.laboratoryService = laboratoryService;
    }

    @PostMapping
    public void setLaboratory(@RequestBody Laboratory laboratory) {
        this.laboratoryService.setLaboratory(laboratory);
    }

    @GetMapping("/{laboratoryId}")
    public Laboratory getLaboratory(@PathVariable Long laboratoryId) {
        return this.laboratoryService.getLaboratory(laboratoryId);
    }

    @GetMapping(value = "/all")
    public List<Laboratory> getLaboratories() {
        return this.laboratoryService.getLaboratories();
    }

    @DeleteMapping("/{laboratoryId}")
    public void deleteLaboratoy(@PathVariable Long laboratoryId) {
        this.laboratoryService.deleteLaboratory(laboratoryId);
    }

    @PutMapping("/{laboratoryId}/setTitle")
    public void updateTitle(@PathVariable Long laboratoryId, @RequestParam String title) {
        this.laboratoryService.updateTitle(laboratoryId, title);
    }

}