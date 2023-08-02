package space.artexplorer.api.collaboratorium;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollaboratoriumRepository extends JpaRepository<Collaboratorium, Long> {
}
