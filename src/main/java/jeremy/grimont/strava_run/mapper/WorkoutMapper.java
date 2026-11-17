package jeremy.grimont.strava_run.mapper;

import jeremy.grimont.strava_run.dto.WorkoutResponseDTO;
import jeremy.grimont.strava_run.dto.request.CreateBikingRequest;
import jeremy.grimont.strava_run.dto.request.CreateRunningRequest;
import jeremy.grimont.strava_run.dto.request.CreateWorkoutRequest;
import jeremy.grimont.strava_run.entity.EffortType;
import jeremy.grimont.strava_run.entity.workout.Biking;
import jeremy.grimont.strava_run.entity.workout.MovingSport;
import jeremy.grimont.strava_run.entity.workout.Running;
import jeremy.grimont.strava_run.entity.workout.Workout;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.OffsetDateTime;

@Component
public class WorkoutMapper {
    public Workout toEntity(CreateWorkoutRequest request) {
        return switch (request) {
            case CreateRunningRequest r -> {
                Running running = new Running();
                this.applyCommonFields(running, r);
                running.setDistance(r.distance());
                running.setMovingTime(Duration.ofSeconds(r.movingTime()));
                yield running;
            }
            case CreateBikingRequest b -> {
                Biking biking = new Biking();
                this.applyCommonFields(biking, b);
                biking.setDistance(b.distance());
                biking.setMovingTime(Duration.ofSeconds(b.movingTime()));
                yield biking;

            }
        };
    }

    public WorkoutResponseDTO toResponse(Workout workout) {

        Long distance = null;
        Long movingTime = null;
        if(workout instanceof MovingSport ms) {
            distance = ms.getDistance();
            movingTime = ms.getMovingTime() != null ? ms.getMovingTime().toSeconds() : null;

        }

        Long id = workout.getId();
        String title = workout.getTitle();
        String description = workout.getDescription();
        OffsetDateTime date = workout.getDate();
        Long totalTime = workout.getTotalTime() != null ? workout.getTotalTime().toSeconds() : null;
        String discipline = workout.getDiscipline();
        Integer calories = workout.getCalories();
        EffortType effortType = workout.getEffortType();

        return new WorkoutResponseDTO(id, title, description,date,totalTime,discipline,calories,effortType, distance, movingTime);
    }

    private void applyCommonFields(Workout w, CreateWorkoutRequest r)
    {
        w.setTitle(r.title());
        w.setDescription(r.description());
        w.setDate(r.date());
        w.setTotalTime(Duration.ofSeconds(r.totalTime()));
        w.setCalories(r.calories());
        w.setEffortType(r.effortType());
    }
}
