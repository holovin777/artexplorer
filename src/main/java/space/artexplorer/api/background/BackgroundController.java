package space.artexplorer.api.background;

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

    @GetMapping(path = "{backgroundId}")
    public Background getBackground(@PathVariable Long backgroundId) {
        return this.backgroundService.getBackground(backgroundId);
    }

    @PostMapping
    public void setBackground(@RequestBody Background background) {
        this.backgroundService.setBackground(background);
    }

    @DeleteMapping(path="{backgroundId}")
    public void deleteBackground(@PathVariable Long backgroundId) {
        this.backgroundService.deleteBackground(backgroundId);
    }

    @PutMapping(path = "{backgroundId}/update")
    public void updateBackground(
            @PathVariable Long backgroundId,
            @RequestParam(required = false) String backgroundUrl,
            @RequestParam(required = false) String backgroundTextColor,
            @RequestParam(required = false) String backgroundTitleTextColor
    )
    {
        this.backgroundService.updateBackground(backgroundId, backgroundUrl, backgroundTextColor, backgroundTitleTextColor);
    }
    
    @PutMapping(path = "{backgroundId}/laboratory")
    public void setLaboratory(@PathVariable Long backgroundId, @RequestParam String laboratoryId) {
        this.backgroundService.setLaboratory(backgroundId, laboratoryId);
    }

}
