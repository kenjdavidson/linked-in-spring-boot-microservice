package kjd.linkedin.explorecali.tour;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kjd.linkedin.explorecali.common.Difficulty;
import kjd.linkedin.explorecali.common.Region;

@Service
public class TourService {
    private TourRepository tourRepository;
    private TourPackageRepository tourPackageRepository;

    @Autowired
    public TourService(TourRepository tourRepository,
                        TourPackageRepository tourPackageRepository) {
        this.tourRepository = tourRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    public Tour createTour(String tourPackageCode,
                            String title, String description, String blurb,
                            BigDecimal price, String duration, String keywords,
                            Region region, Difficulty difficulty) {
        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageCode)
            .orElseThrow(() -> new RuntimeException("Tour package does not exist: " + tourPackageCode));

        Tour tour = new Tour(title, description, blurb, 
                             price, duration, keywords, 
                             tourPackage, region, difficulty);
        return tourRepository.save(tour);
    }

    public Iterable<Tour> lookup() {
        return tourRepository.findAll();
    }

    public Long total() {
        return tourRepository.count();
    }
}
