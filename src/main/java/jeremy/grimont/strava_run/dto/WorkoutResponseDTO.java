package jeremy.grimont.strava_run.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class WorkoutResponseDTO {
    private Long id;
    private String title;
    private String description;
    private LocalDate date;
    private Integer durationInMinutes;
    private String discipline;
    private Integer calories;
    private String effortTypeLabel;

    //MOVING SPORTS
    private Long distance;
    private Long movingTime;
    private Long totalTime;

}