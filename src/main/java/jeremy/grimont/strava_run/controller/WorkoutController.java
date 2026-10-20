package jeremy.grimont.strava_run.controller;

import jeremy.grimont.strava_run.dto.WorkoutResponseDTO;
import jeremy.grimont.strava_run.entity.workout.MovingSport;
import jeremy.grimont.strava_run.entity.workout.Workout;
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

    @GetMapping
    public List<WorkoutResponseDTO> getAllWorkouts() {
        List<Workout> workouts = service.findAll();

        return workouts.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public Workout update(@RequestBody @Validated Workout updatedWorkout) {
        return service.update(updatedWorkout);
    }

    @PostMapping
    public Workout create(@RequestBody @Validated Workout newWorkout) {
        return service.create(newWorkout);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    private WorkoutResponseDTO convertToDTO(Workout workout) {

        WorkoutResponseDTO dto = new WorkoutResponseDTO();

        dto.setId(workout.getId());
        dto.setTitle(workout.getTitle());
        dto.setDescription(workout.getDescription());
        dto.setDate(workout.getDate());
        dto.setDurationInMinutes(workout.getDurationInMinutes());
        dto.setDiscipline(workout.getDiscipline());
        dto.setCalories(workout.getCalories());
        dto.setEffortTypeLabel(workout.getEffortType().getLabel());

        switch (workout) {
            case MovingSport ms -> {
                dto.setDistance(ms.getDistance());
                dto.setTotalTime(ms.getTotalTime());
                dto.setMovingTime(ms.getMovingTime());
            }
            default -> {
            }
        }

        return dto;
    }

}
