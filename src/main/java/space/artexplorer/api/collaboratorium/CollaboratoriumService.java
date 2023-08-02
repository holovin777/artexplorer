package space.artexplorer.api.collaboratorium;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CollaboratoriumService {

    private final CollaboratoriumRepository collaboratoriumRepository;

    @Autowired
    public CollaboratoriumService(CollaboratoriumRepository collaboratoriumRepository) {
        this.collaboratoriumRepository = collaboratoriumRepository;
    }

    public List<Collaboratorium> getCollaboratoriums() {
        return this.collaboratoriumRepository.findAll();
    }

    @Transactional
    public void setCollaboratorium(Collaboratorium collaboratorium) {
        this.collaboratoriumRepository.save(collaboratorium);
    }

}
