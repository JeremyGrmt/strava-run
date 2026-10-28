package jeremy.grimont.strava_run.entity.workout;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("BIKING")
public class Biking extends MovingSport {
    @Override
    public String getDiscipline() {return "BIKING";}
}
