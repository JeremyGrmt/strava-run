package jeremy.grimont.strava_run.repository;

import jeremy.grimont.strava_run.entity.EffortType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EffortTypeRepository extends JpaRepository<EffortType, Long> {

}