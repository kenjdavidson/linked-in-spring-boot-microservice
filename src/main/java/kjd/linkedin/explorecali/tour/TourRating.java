package kjd.linkedin.explorecali.tour;

import java.io.Serializable;
import java.text.MessageFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import kjd.linkedin.explorecali.customer.Customer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
@Getter
@Setter
public class TourRating {   

    @EmbeddedId
    private TourRatingKey key;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private String comment;

    @Embeddable
    @NoArgsConstructor(access = AccessLevel.PACKAGE)
    @AllArgsConstructor
    public static class TourRatingKey implements Serializable {        
        @OneToOne
        private Customer customer;

        @ManyToOne
        private Tour tour;                

        @Override 
        public String toString() {
            return MessageFormat.format("Customer: {0}, Tour: {1}", customer.getId(), tour.getId());
        }
    }
}
