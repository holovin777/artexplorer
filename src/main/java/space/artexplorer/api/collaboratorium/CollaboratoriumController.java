package space.artexplorer.api.collaboratorium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/collaboratorium")
public class CollaboratoriumController {
    private final CollaboratoriumService collaboratoriumService;

    @Autowired
    public CollaboratoriumController(CollaboratoriumService collaboratoriumService) {
        this.collaboratoriumService = collaboratoriumService;
    }

    @GetMapping(path = "all")
    public List<Collaboratorium> getCollaboratoriums() {
        return collaboratoriumService.getCollaboratoriums();
    }

    @PostMapping
    public void setCollaboratorium(@RequestBody Collaboratorium collaboratorium) {
        this.collaboratoriumService.setCollaboratorium(collaboratorium);
    }
}
