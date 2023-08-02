package space.artexplorer.api.background;

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
@RequestMapping("api/v1/background")

public class BackgroundController {
    private final BackgroundService backgroundService;

    @Autowired
    public BackgroundController(BackgroundService backgroundService) {
        this.backgroundService = backgroundService;
    }

    @GetMapping(path = "all")
    List<Background> getBackgrounds() {
        return backgroundService.getBackgrounds();
    }

    @PostMapping
    public void setBackground(@RequestBody Background background) {
        this.backgroundService.setBackground(background);
    }

    @DeleteMapping(path="{backgroundId}")
    public void deleteBackground(@PathVariable Long backgroundId) {
        this.backgroundService.deleteBackground(backgroundId);
    }
}
