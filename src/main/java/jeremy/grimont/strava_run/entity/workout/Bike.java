package jeremy.grimont.strava_run.entity.workout;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BIKE")
public class Bike extends MovingSport {
}
