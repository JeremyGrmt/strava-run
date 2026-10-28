package jeremy.grimont.strava_run.entity.workout;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jeremy.grimont.strava_run.entity.EffortType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    private LocalDate date;
    @Positive(message = "Total time can only be positive")
    private Long totalTime; // (ms)
    public String getDiscipline() {return "OTHER";}
    @PositiveOrZero(message = "Calories cannot be negative")
    private Integer calories;

    @Enumerated(EnumType.STRING)
    private EffortType effortType;
}