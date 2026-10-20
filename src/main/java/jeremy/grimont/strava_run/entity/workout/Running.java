package jeremy.grimont.strava_run.entity.workout;


import jakarta.persistence.*;

@Entity
@DiscriminatorValue("RUNNING")
public class Running extends MovingSport {
}
