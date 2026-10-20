package jeremy.grimont.strava_run.service;

import jeremy.grimont.strava_run.entity.EffortType;
import jeremy.grimont.strava_run.entity.workout.Workout;
import jeremy.grimont.strava_run.exception.WorkoutNotFoundException;
import jeremy.grimont.strava_run.repository.EffortTypeRepository;
import jeremy.grimont.strava_run.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import jeremy.grimont.strava_run.dto.WorkoutResponseDTO;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final EffortTypeRepository effortTypeRepository;

    public Workout create(@NonNull Workout workout) {
        Long effortTypeId = workout.getEffortType().getId();
        EffortType effortType = effortTypeRepository.findById(effortTypeId).orElseThrow();

        workout.setEffortType(effortType);
        return workoutRepository.save(workout);

    }

    public Workout update(@NonNull Workout workout) {
        // check existance
        // get current WO
        // check updated fields
        // update fields
        // save

        Long workoutId = workout.getId();

        return workout;
    }

    public List<Workout> findAll() {
        return workoutRepository.findAll();
    }

    public Workout findById(Long workoutId) {
        return workoutRepository.findById(workoutId)
                .orElseThrow(() -> new WorkoutNotFoundException("Workout with id " + workoutId + " not found"));
    }

    public void deleteById(Long id) {
        workoutRepository.deleteById(id);
    }
}
