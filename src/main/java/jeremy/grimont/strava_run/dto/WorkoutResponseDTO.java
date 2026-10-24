package jeremy.grimont.strava_run.dto;

import java.time.LocalDate;


public record WorkoutResponseDTO(
        Long id, String title, String description, LocalDate date,
        Long totalTime, String discipline, Integer calories,
        String effortTypeLabel, Long distance, Long movingTime
) { }