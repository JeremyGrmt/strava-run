package jeremy.grimont.strava_run.controller;

import jakarta.validation.Valid;
import jeremy.grimont.strava_run.dto.WorkoutResponseDTO;
import jeremy.grimont.strava_run.dto.request.CreateWorkoutRequest;
import jeremy.grimont.strava_run.entity.workout.Workout;
import jeremy.grimont.strava_run.mapper.WorkoutMapper;
import jeremy.grimont.strava_run.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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

    public Workout update(@RequestBody @Validated Workout updatedWorkout) {
        return service.update(updatedWorkout);
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
