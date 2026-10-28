package jeremy.grimont.strava_run.controller;

import jakarta.validation.Valid;
import jeremy.grimont.strava_run.dto.WorkoutResponseDTO;
import jeremy.grimont.strava_run.dto.request.CreateWorkoutRequest;
import jeremy.grimont.strava_run.dto.request.UpdateWorkoutRequest;
import jeremy.grimont.strava_run.entity.workout.Workout;
import jeremy.grimont.strava_run.mapper.WorkoutMapper;
import jeremy.grimont.strava_run.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workouts")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService service;
    private final WorkoutMapper mapper;

    @GetMapping
    public List<WorkoutResponseDTO> getAllWorkouts() {
        List<Workout> workouts = service.findAll();

        return workouts.stream()
                .map(mapper::toResponse)
                .toList();
    }

    @PutMapping("/{id}")
    public WorkoutResponseDTO update(@PathVariable Long id, @RequestBody @Valid UpdateWorkoutRequest request) {
        Workout wUpdated =  service.update(id, request);
        return mapper.toResponse(wUpdated);
    }

    @PostMapping
    public WorkoutResponseDTO create(@RequestBody @Valid CreateWorkoutRequest request) {
        Workout wSaved = service.create(request);
        return mapper.toResponse(wSaved);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

}
