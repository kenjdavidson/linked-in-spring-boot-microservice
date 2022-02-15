package kjd.linkedin.explorecali.tour;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface TourPackageRepository extends CrudRepository<TourPackage,String> {

    Optional<TourPackage> findByName(String tourPackageCode);
    
}
