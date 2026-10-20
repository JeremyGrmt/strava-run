package jeremy.grimont.strava_run.controller;

import jeremy.grimont.strava_run.entity.EffortType;
import jeremy.grimont.strava_run.repository.EffortTypeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/efforts")
public class EffortTypeController {

    private final EffortTypeRepository repository;


    public EffortTypeController(EffortTypeRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<EffortType> getAllEfforts() {
        return repository.findAll();
    }

    @PostMapping
    public EffortType createEffortType(@RequestBody EffortType newEffortType) {
        return repository.save(newEffortType);
    }
    @DeleteMapping
    public void deleteEffortType(@RequestBody EffortType effortType) {
        repository.deleteById(effortType.getId());
    }



}
