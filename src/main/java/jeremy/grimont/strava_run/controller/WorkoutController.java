package jeremy.grimont.strava_run.controller;

import jakarta.validation.Valid;
import jeremy.grimont.strava_run.dto.WorkoutResponseDTO;
import jeremy.grimont.strava_run.dto.request.CreateWorkoutRequest;
import jeremy.grimont.strava_run.dto.request.PatchWorkoutRequest;
import jeremy.grimont.strava_run.dto.request.UpdateWorkoutRequest;
import jeremy.grimont.strava_run.entity.workout.Workout;
import jeremy.grimont.strava_run.mapper.WorkoutMapper;
import jeremy.grimont.strava_run.service.WorkoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @GetMapping("/{id}")
    public WorkoutResponseDTO getWorkoutById(@PathVariable Long id) {
        Workout wGet = service.findById(id);
        return mapper.toResponse(wGet);
    }

    @PostMapping
    public ResponseEntity<WorkoutResponseDTO> create(@RequestBody @Valid CreateWorkoutRequest request) {
        Workout wSaved = service.create(request);
        WorkoutResponseDTO dto = mapper.toResponse(wSaved);
        URI location = URI.create("/api/workouts/" + dto.id());

        return ResponseEntity.created(location).body(dto);
    }


    @PutMapping("/{id}")
    public WorkoutResponseDTO update(@PathVariable Long id, @RequestBody @Valid UpdateWorkoutRequest request) {
        Workout wUpdated =  service.update(id, request);

        return mapper.toResponse(wUpdated);
    }

    @PatchMapping("/{id}")
    public WorkoutResponseDTO patch(@PathVariable Long id, @RequestBody @Valid PatchWorkoutRequest request) {
        Workout wPatched =  service.patch(id, request);

        return mapper.toResponse(wPatched);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

}
