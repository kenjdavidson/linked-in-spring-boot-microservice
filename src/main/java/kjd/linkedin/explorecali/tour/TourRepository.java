package kjd.linkedin.explorecali.tour;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import kjd.linkedin.explorecali.common.Difficulty;
import kjd.linkedin.explorecali.common.Region;

public interface TourRepository extends CrudRepository<Tour, Long> {
    
    List<Tour> findByTourPackageCode(@Param("code") String code);

    Integer countByRegion(Region region);

    Integer countByDifficulty(Difficulty difficulty);

}
