package kjd.linkedin.explorecali.tour.response;

import kjd.linkedin.explorecali.tour.TourRating;
import lombok.Data;

@Data
public class TourRatingResponse {
    private Integer score;
    private String comment;

    public TourRatingResponse(TourRating rating) {
        this.score = rating.getScore();
        this.comment = rating.getComment();
    }
}
