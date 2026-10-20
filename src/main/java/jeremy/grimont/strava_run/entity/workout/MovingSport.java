package jeremy.grimont.strava_run.entity.workout;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public abstract class MovingSport extends Workout {

    @Positive(message = "Distance can only be positive")
    private Long distance; // (m)
    @Positive(message = "Moving Time can only be positive")
    private Long movingTime; //
    @Positive(message = "Total time can only be positive")
    private Long totalTime; // (ms)

}