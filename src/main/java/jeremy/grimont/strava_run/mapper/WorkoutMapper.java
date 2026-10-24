package jeremy.grimont.strava_run.mapper;

import jeremy.grimont.strava_run.dto.WorkoutResponseDTO;
import jeremy.grimont.strava_run.dto.request.CreateBikingRequest;
import jeremy.grimont.strava_run.dto.request.CreateRunningRequest;
import jeremy.grimont.strava_run.dto.request.CreateWorkoutRequest;
import jeremy.grimont.strava_run.entity.workout.Biking;
import jeremy.grimont.strava_run.entity.workout.MovingSport;
import jeremy.grimont.strava_run.entity.workout.Running;
import jeremy.grimont.strava_run.entity.workout.Workout;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class WorkoutMapper {
    public Workout toEntity(CreateWorkoutRequest request) {
        return switch (request) {
            case CreateRunningRequest r -> {
                Running running = new Running();
                this.applyCommonFields(running, r);
                running.setDistance(r.distance());
                running.setMovingTime(r.movingTime());
                yield running;
            }
            case CreateBikingRequest b -> {
                Biking biking = new Biking();
                this.applyCommonFields(biking, b);
                biking.setDistance(b.distance());
                biking.setMovingTime(b.movingTime());
                yield biking;

            }
        };
    }

    public WorkoutResponseDTO toResponse(Workout workout) {

        Long distance = null;
        Long movingTime = null;
        if(workout instanceof MovingSport ms) {
            distance = ms.getDistance();
            movingTime = ms.getMovingTime();

        }

        Long id = workout.getId();
        String title = workout.getTitle();
        String description = workout.getDescription();
        LocalDate date = workout.getDate();
        Long totalTime = workout.getTotalTime();
        String discipline = workout.getDiscipline();
        Integer calories = workout.getCalories();
        String effortTypeLabel = workout.getEffortType().getLabel();

        return new WorkoutResponseDTO(id, title, description,date,totalTime,discipline,calories,effortTypeLabel, distance, movingTime);
    }

    private void applyCommonFields(Workout w, CreateWorkoutRequest r)
    {
        w.setTitle(r.title());
        w.setDescription(r.description());
        w.setDate(r.date());
        w.setTotalTime(r.totalTime());
        w.setCalories(r.calories());
    }
}
