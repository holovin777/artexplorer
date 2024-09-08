package space.artexplorer.api.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import space.artexplorer.api.laboratory.LaboratoryRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SocialService {
    private final SocialRepository socialRepository;

    @Autowired
    public SocialService(SocialRepository socialRepository, LaboratoryRepository laboratoryRepository) {
        this.socialRepository = socialRepository;
    }

    public List<Social> getSocials() {
        return this.socialRepository.findAll();
    }

    @Transactional
    public void setSocial(Social social) {
        this.socialRepository.save(social);
    }

    public Social getSocial(Long socialId) {
        Optional<Social> socialOptional = socialRepository.findById(socialId);
        if (socialOptional.isPresent()) {
            return socialOptional.get();
        }
        throw new IllegalStateException("Social with ID " + socialId + " doesn't exists");
    }

    @Transactional
    public void deleteSocial(Long socialId) {
        Optional<Social> socialOptional = socialRepository.findById(socialId);
        if (socialOptional.isPresent()) {
            Social social = socialOptional.get();
            this.socialRepository.delete(social);
        } else {
            throw new IllegalStateException("Social with ID " + socialId + " doesn't exists");
        }
    }

    @Transactional
    public void updateSocial(Long socialId, String socialLink, String socialImageLink, String socialDescription) {
        Optional<Social> socialOptional = this.socialRepository.findById(socialId);
        if (socialOptional.isPresent()) {
            Social social = socialOptional.get();
            if (socialLink != null) {
                social.setLink(socialLink);
                this.socialRepository.save(social);
            }
            if (socialDescription != null) {
                social.setDescription(socialDescription);
                this.socialRepository.save(social);
            }
            if (socialImageLink != null) {
                social.setImageLink(socialImageLink);
                this.socialRepository.save(social);
            }
        } else {
            throw new IllegalStateException("Social with ID " + socialId + " doesn't exists");
        }
    }

}
