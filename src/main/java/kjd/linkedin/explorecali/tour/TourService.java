package kjd.linkedin.explorecali.tour;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Tour createTour(String tourPackageName, String title, Map<String,String> details) {
        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName)
            .orElseThrow(() -> new RuntimeException("Tour package does not exist: " + tourPackageName));

        Tour tour = new Tour(title, tourPackage, details);
        return tourRepository.save(tour);
    }

    public Iterable<Tour> lookup() {
        return tourRepository.findAll();
    }

    public Long total() {
        return tourRepository.count();
    }
}
