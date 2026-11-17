package jeremy.grimont.strava_run.entity.workout;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jeremy.grimont.strava_run.entity.EffortType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.OffsetDateTime;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "discipline")
@DiscriminatorValue("OTHER")
@Getter
@Setter
@NoArgsConstructor

public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String title;
    private String description;
    private OffsetDateTime date;
    private Duration totalTime; // (s)
    public String getDiscipline() {return "OTHER";}
    @PositiveOrZero(message = "Calories cannot be negative")
    private Integer calories;

    @Enumerated(EnumType.STRING)
    private EffortType effortType;
}