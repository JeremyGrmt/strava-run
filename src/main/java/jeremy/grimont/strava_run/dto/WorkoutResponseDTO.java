package jeremy.grimont.strava_run.dto;

import jeremy.grimont.strava_run.entity.EffortType;

import java.time.OffsetDateTime;


public record WorkoutResponseDTO(
        Long id, String title, String description, OffsetDateTime date,
        Long totalTime, String discipline, Integer calories,
        EffortType effortType, Long distance, Long movingTime
) { }