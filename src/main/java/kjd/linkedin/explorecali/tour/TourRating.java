package kjd.linkedin.explorecali.tour;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
@Getter
@Setter
public class TourRating {   

    @Id
    private String id;

    @NotNull 
    private String customerId;

    @NotNull
    private String tourId;

    @Min(0)
    @Max(5)
    private Integer score;

    @Size(max = 255)
    private String comment;

    public TourRating(String customerId, String tourId, Integer score, String comment) {
        this.customerId = customerId;
        this.tourId = tourId;
        this.score = score;
        this.comment = comment;
    }
}
