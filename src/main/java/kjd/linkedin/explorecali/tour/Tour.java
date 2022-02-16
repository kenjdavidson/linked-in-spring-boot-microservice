package kjd.linkedin.explorecali.tour;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import kjd.linkedin.explorecali.common.Difficulty;
import kjd.linkedin.explorecali.common.Region;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Tour {

    private static final int SIZE_1000 = 1000;
    private static final int SIZE_2000 = 2000;

    @Id
    @GeneratedValue    
    @Getter
    @Setter
    private Long id;

    @Column
    @Getter
    @Setter
    private String title;

    @Column(length = SIZE_2000)
    @Getter
    @Setter
    private String description;

    @Column(length = SIZE_1000)
    @Getter
    @Setter
    private String blurb;

    @Column
    @Getter
    @Setter
    private BigDecimal price;

    @Column
    @Getter
    @Setter
    private String duration;

    @Column
    @Getter
    @Setter
    private String keywords;

    @ManyToOne
    @Getter
    @Setter
    private TourPackage tourPackage;

    @Column
    @Enumerated
    @Getter
    @Setter
    private Difficulty difficulty;

    @Column
    @Enumerated
    @Getter
    @Setter
    private Region region;

    public Tour(String title,
                String description,
                String blurb,
                BigDecimal price,
                String duration,
                String keywords,
                TourPackage tourPackage,
                Region region,
                Difficulty difficulty) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.keywords = keywords;
        this.tourPackage = tourPackage;
        this.region = region;
        this.difficulty = difficulty;
    }
}
