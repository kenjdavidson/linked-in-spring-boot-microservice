package kjd.linkedin.explorecali.tour;

import org.springframework.data.repository.CrudRepository;

import kjd.linkedin.explorecali.common.Difficulty;
import kjd.linkedin.explorecali.common.Region;

public interface TourRepository extends CrudRepository<Tour, Long> {
    
    Integer countByRegion(Region region);

    Integer countByDifficulty(Difficulty difficulty);
    
}
