package jeremy.grimont.strava_run.repository;

import jeremy.grimont.strava_run.entity.workout.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

}