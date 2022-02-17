package kjd.linkedin.explorecali.tour;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void createTourRating(@PathVariable("tourId") String tourId, 
                                 @RequestBody @Validated TourRatingRequest createTourRating) {
        Customer customer = verifyCustomerExists(createTourRating.getCustomerId());
        Tour tour = verifyTourExists(tourId);

        TourRating rating = new TourRating(customer.getId(), tour.getId(), createTourRating.getScore(), createTourRating.getComment());
        tourRatingRepository.save(rating);
    }

    @GetMapping
    public List<TourRatingResponse> getRatingsByTour(@PathVariable("tourId") String tourId) {
        verifyTourExists(tourId);
            
        return tourRatingRepository.findByTourId(tourId)
                .orElseThrow(() -> new NoSuchElementException("Tour has no ratings"))    
                .stream()
                .map(TourRatingResponse::new)
                .collect(Collectors.toList());
    }

    @PutMapping
    public TourRatingResponse updateRating(@PathVariable("tourId") String tourId, 
                                           @RequestBody @Validated TourRatingRequest createTourRating) {
        Customer customer = verifyCustomerExists(createTourRating.getCustomerId());
        Tour tour = verifyTourExists(tourId);

        TourRating rating = verifyExists(tour.getId(), customer.getId());
        rating.setScore(createTourRating.getScore());
        rating.setComment(createTourRating.getComment());

        return new TourRatingResponse(tourRatingRepository.save(rating));   
    }

    @PatchMapping
    public TourRatingResponse patchRating(@PathVariable("tourId") String tourId, 
                                          @RequestBody @Validated TourRatingRequest createTourRating) {
        Customer customer = verifyCustomerExists(createTourRating.getCustomerId());
        Tour tour = verifyTourExists(tourId);
        TourRating rating = verifyExists(tour.getId(), customer.getId());

        if (createTourRating.getScore() != null) {
            rating.setScore(createTourRating.getScore());
        }
        if (createTourRating.getComment() != null) {
            rating.setComment(createTourRating.getComment());
        }

        return new TourRatingResponse(tourRatingRepository.save(rating));
    }

    @DeleteMapping(path = "/{customerId}")
    public TourRatingResponse deleteRating(@PathVariable("tourId") String tourId, 
                                           @PathVariable("customerId") String customerId) {
        Customer customer = verifyCustomerExists(customerId);
        Tour tour = verifyTourExists(tourId);
        TourRating rating = verifyExists(tour.getId(), customer.getId());

        tourRatingRepository.delete(rating);
        return new TourRatingResponse(rating);
    }

    @GetMapping(path = "/average")
    public Map<String,Object> getAverageByTour(@PathVariable("tourId") String tourId) {        
        verifyTourExists(tourId);

        List<TourRating> ratings = tourRatingRepository.findByTourId(tourId)
            .orElseThrow(() -> new NoSuchElementException("Tour has no ratings"));        
        
        Map<String,Object> result = new HashMap<>();
        result.put("average", ratings.stream().collect(Collectors.averagingDouble(TourRating::getScore)));
        result.put("ratings", ratings.size());
        return result;
    }

    private Customer verifyCustomerExists(String customerId) {
        return customerRepository
            .findById(customerId)
            .orElseThrow(() -> new NoSuchElementException(MessageFormat.format("Customer does not exist {0}", customerId)));
    }

    private Tour verifyTourExists(String tourId) {
        return tourRepository
            .findById(tourId)
            .orElseThrow(() -> new NoSuchElementException(MessageFormat.format("Tour does not exist {0}, {1}", tourId)));
    }

    private TourRating verifyExists(String tourId, String customerId) {
        return tourRatingRepository
            .findByTourIdAndCustomerId(tourId, customerId)
            .orElseThrow(() -> new NoSuchElementException(MessageFormat.format("TourRating does not exist {0}, {1}", tourId, customerId)));
    }

}
