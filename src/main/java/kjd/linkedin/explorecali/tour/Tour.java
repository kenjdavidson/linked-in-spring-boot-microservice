package kjd.linkedin.explorecali.tour;

import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Tour {

    @Id
    @Getter
    @Setter
    private String id;

    @Getter
    @Setter
    @Indexed
    private String title;

    @Indexed
    @Getter
    @Setter
    private String tourPackageCode;

    @Getter
    @Setter 
    private String tourPackageName;

    @Getter
    @Setter
    private Map<String, String> details;

    public Tour(String title,                
                TourPackage tourPackage,
                Map<String,String> details) {
        this.title = title;        
        this.tourPackageCode = tourPackage.getCode();
        this.tourPackageName = tourPackage.getName();
        this.details = details;
    }
}
