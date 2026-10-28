package jeremy.grimont.strava_run.dto;

import jeremy.grimont.strava_run.entity.EffortType;

import java.time.LocalDate;


public record WorkoutResponseDTO(
        Long id, String title, String description, LocalDate date,
        Long totalTime, String discipline, Integer calories,
        EffortType effortType, Long distance, Long movingTime
) { }