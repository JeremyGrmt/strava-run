package jeremy.grimont.strava_run.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CreateRunningRequest(
        @NotBlank(message = "Title is required") String title,
        String description,
        @NotNull(message = "Date is required")
        LocalDate date,
        @NotNull(message = "Duration is required")
        @Positive(message = "Duration must be positive")
        Long totalTime,
        @NotNull(message = "Calories count is required")
        @Positive(message = "Calories count must be positive")
        Integer calories,
        @NotNull(message = "Effort type is required")
        Long effortTypeId,
        @NotBlank(message = "Discipline is required")
        String discipline,
        Long distance,
        Long movingTime
        ) implements CreateWorkoutRequest{

}
