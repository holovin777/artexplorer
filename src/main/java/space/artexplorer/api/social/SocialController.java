package space.artexplorer.api.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/social")

public class SocialController {
    private final SocialService socialService;

    @Autowired
    public SocialController(SocialService socialService) {
        this.socialService = socialService;
    }

    @GetMapping(path = "all")
    List<Social> getSocials() {
        return socialService.getSocials();
    }

    @PostMapping
    public void setSocial(@RequestBody Social social) {
        this.socialService.setSocial(social);
    }

    @DeleteMapping(path="{socialId}")
    public void deleteSocial(@PathVariable Long socialId) {
        this.socialService.deleteSocial(socialId);
    }

    @PutMapping(path = "{socialId}/update")
    public void updateSocial(
            @PathVariable Long socialId,
            @RequestParam(required = false) String socialLink,
            @RequestParam(required = false) String socialDescription
    )
    {
        this.socialService.updateSocial(socialId, socialLink, socialDescription);
    }
}
