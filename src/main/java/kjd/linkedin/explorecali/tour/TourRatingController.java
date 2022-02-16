package kjd.linkedin.explorecali.tour;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kjd.linkedin.explorecali.customer.Customer;
import kjd.linkedin.explorecali.customer.CustomerRepository;
import kjd.linkedin.explorecali.tour.TourRating.TourRatingKey;
import kjd.linkedin.explorecali.tour.request.TourRatingRequest;
import kjd.linkedin.explorecali.tour.response.TourRatingResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TourRatingController {
    @Autowired
    private TourRatingRespository tourRatingRepository;

    @Autowired
    private TourRepository tourRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @ResponseStatus(HttpStatus.NOT_FOUND) 
    @ExceptionHandler(NoSuchElementException.class)
    public String return404(NoSuchElementException ex) {
        return ex.getLocalizedMessage();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTourRating(@PathVariable("tourId") Long tourId, 
                                 @RequestBody @Validated TourRatingRequest createTourRating) {
        Customer customer = verifyExists("Customer", createTourRating.getCustomerId(), customerRepository);
        Tour tour = verifyExists("Tour", tourId, tourRepository);

        TourRatingKey key = new TourRatingKey(customer, tour);
        TourRating rating = new TourRating(key, createTourRating.getScore(), createTourRating.getComment());
        tourRatingRepository.save(rating);
    }

    @GetMapping
    public List<TourRatingResponse> getRatingsByTour(@PathVariable("tourId") Long tourId) {
        verifyExists("Tour", tourId, tourRepository);
            
        return tourRatingRepository.findByKeyTourId(tourId)
                .orElseThrow(() -> new NoSuchElementException("Tour has no ratings"))    
                .stream()
                .map(TourRatingResponse::new)
                .collect(Collectors.toList());
    }

    @PutMapping
    public TourRatingResponse updateRating(@PathVariable("tourId") Long tourId, 
                                           @RequestBody @Validated TourRatingRequest createTourRating) {
        Customer customer = verifyExists("Customer", createTourRating.getCustomerId(), customerRepository);
        Tour tour = verifyExists("Tour", tourId, tourRepository);

        TourRatingKey key = new TourRatingKey(customer, tour);
        TourRating rating = verifyExists("Rating", key, tourRatingRepository);
        rating.setScore(createTourRating.getScore());
        rating.setComment(createTourRating.getComment());

        return new TourRatingResponse(tourRatingRepository.save(rating));   
    }

    @PatchMapping
    public TourRatingResponse patchRating(@PathVariable("tourId") Long tourId, 
                                          @RequestBody @Validated TourRatingRequest createTourRating) {
        Customer customer = verifyExists("Customer", createTourRating.getCustomerId(), customerRepository);
        Tour tour = verifyExists("Tour", tourId, tourRepository);

        TourRatingKey key = new TourRatingKey(customer, tour);
        TourRating rating = verifyExists("Rating", key, tourRatingRepository);

        if (createTourRating.getScore() != null) {
            rating.setScore(createTourRating.getScore());
        }
        if (createTourRating.getComment() != null) {
            rating.setComment(createTourRating.getComment());
        }

        return new TourRatingResponse(tourRatingRepository.save(rating));
    }

    @DeleteMapping(path = "/{customerId}")
    public TourRatingResponse deleteRating(@PathVariable("tourId") Long tourId, 
                                           @PathVariable("customerId") Long customerId) {
        Customer customer = verifyExists("Customer", customerId, customerRepository);
        Tour tour = verifyExists("Tour", tourId, tourRepository);

        TourRatingKey key = new TourRatingKey(customer, tour);
        TourRating rating = verifyExists("Rating", key, tourRatingRepository);

        tourRatingRepository.delete(rating);
        return new TourRatingResponse(rating);
    }

    @GetMapping(path = "/average")
    public Map<String,Object> getAverageByTour(@PathVariable("tourId") Long tourId) {        
        verifyExists("Tour", tourId, tourRepository);

        List<TourRating> ratings = tourRatingRepository.findByKeyTourId(tourId)
            .orElseThrow(() -> new NoSuchElementException("Tour has no ratings"));        
        
        Map<String,Object> result = new HashMap<>();
        result.put("average", ratings.stream().collect(Collectors.averagingDouble(TourRating::getScore)));
        result.put("ratings", ratings.size());
        return result;
    }

    /**
     * Adding the type here isn't the best, but it's quick.  Would be better if the method pulled out
     * the name of the type directly from the repository; thorugh either reflection or providing the
     * type.
     * 
     * @param <T>
     * @param <ID>
     * @param type
     * @param id
     * @param lookup
     * @return
     * @throws NoSuchElementException
     */
    private <T, ID> T verifyExists(String type, ID id, CrudRepository<T, ID> lookup) throws NoSuchElementException {
        return (T) lookup
            .findById(id)
            .orElseThrow(() -> new NoSuchElementException(MessageFormat.format("{0} does not exist {1}", type, id)));
    }
}
