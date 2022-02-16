package kjd.linkedin.explorecali.tour;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import kjd.linkedin.explorecali.tour.TourRating.TourRatingKey;

@RepositoryRestResource(exported = false)
public interface TourRatingRespository extends CrudRepository<TourRating, TourRatingKey> {
    
    Optional<List<TourRating>> findByKeyTourId(Long tourId);

    Optional<TourRating> findByKeyTourIdAndKeyCustomerId(Long tourId, Long customerId);
    
}
