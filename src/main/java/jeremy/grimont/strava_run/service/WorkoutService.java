package jeremy.grimont.strava_run.service;

import jeremy.grimont.strava_run.dto.request.CreateWorkoutRequest;
import jeremy.grimont.strava_run.dto.request.PatchWorkoutRequest;
import jeremy.grimont.strava_run.dto.request.UpdateWorkoutRequest;
import jeremy.grimont.strava_run.entity.EffortType;
import jeremy.grimont.strava_run.entity.workout.MovingSport;
import jeremy.grimont.strava_run.entity.workout.Workout;
import jeremy.grimont.strava_run.exception.EffortTypeNotFoundException;
import jeremy.grimont.strava_run.exception.WorkoutNotFoundException;
import jeremy.grimont.strava_run.mapper.WorkoutMapper;
import jeremy.grimont.strava_run.repository.EffortTypeRepository;
import jeremy.grimont.strava_run.repository.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final EffortTypeRepository effortTypeRepository;
    private final WorkoutMapper mapper;

    public List<Workout> findAll() {
        return workoutRepository.findAll();
    }

    public Workout findById(Long workoutId) {
        return workoutRepository.findById(workoutId)
                .orElseThrow(() -> new WorkoutNotFoundException("Workout with id " + workoutId + " not found"));
    }

    public Workout create(@NonNull CreateWorkoutRequest request) {
        Workout workout = mapper.toEntity(request);
        EffortType effortType = getEffortType(request.effortTypeId());
        workout.setEffortType(effortType);
        return workoutRepository.save(workout);

    }

    @Transactional
    public Workout update(Long id, UpdateWorkoutRequest request) {
        Workout workout = findById(id);
        workout.setTitle(request.title());
        workout.setDescription(request.description());
        workout.setDate(request.date());
        workout.setTotalTime(request.totalTime());
        workout.setCalories(request.calories());
        EffortType effortType = getEffortType(request.effortTypeId());
        workout.setEffortType(effortType);
        if(workout instanceof MovingSport ms) {
            ms.setDistance(request.distance());
            ms.setMovingTime(request.movingTime());
        }

        return workout;
    }

    @Transactional
    public Workout patch(Long id, PatchWorkoutRequest request) {
        Workout workout = findById(id);
        if (request.title() != null) workout.setTitle(request.title());
        if (request.description() != null) workout.setDescription(request.description());
        if (request.date() != null)  workout.setDate(request.date());
        if (request.totalTime() != null)  workout.setTotalTime(request.totalTime());
        if (request.calories() != null) workout.setCalories(request.calories());
        if (request.effortTypeId() != null) {
            EffortType effortType = getEffortType(request.effortTypeId());
            workout.setEffortType(effortType);
        }

        if(workout instanceof MovingSport ms) {
            if (request.distance() != null)ms.setDistance(request.distance());
            if (request.movingTime() != null)ms.setMovingTime(request.movingTime());
        }

        return workout;
    }

    public void deleteById(Long id) {
        if (!workoutRepository.existsById(id)){
            throw new WorkoutNotFoundException("Workout with id " + id + " not found");
        }
        workoutRepository.deleteById(id);
    }

    private EffortType getEffortType(Long effortTypeId) {
        return effortTypeRepository
                .findById(effortTypeId)
                .orElseThrow(() -> new EffortTypeNotFoundException("Effort Type with id " + effortTypeId + " not found"));
    }
}
