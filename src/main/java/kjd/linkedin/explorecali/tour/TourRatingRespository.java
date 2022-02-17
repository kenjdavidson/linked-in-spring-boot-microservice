package kjd.linkedin.explorecali.tour;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(exported = false)
public interface TourRatingRespository extends CrudRepository<TourRating, String> {
    
    Optional<List<TourRating>> findByTourId(String tourId);

    Optional<TourRating> findByTourIdAndCustomerId(String tourId, String customerId);

}
