package jeremy.grimont.strava_run.dto.request;

import jakarta.validation.constraints.Positive;
import jeremy.grimont.strava_run.entity.EffortType;

import java.time.LocalDate;

public record PatchWorkoutRequest(
        String title,
        String description,
        LocalDate date,
        @Positive(message = "Duration must be positive")
        Long totalTime,
        @Positive(message = "Calories count must be positive")
        Integer calories,
        EffortType effortType,
        @Positive(message = "Distance must be positive")
        Long distance,
        @Positive(message = "Moving time must be positive")
        Long movingTime
) {
}
