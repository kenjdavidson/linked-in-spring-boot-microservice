package kjd.linkedin.explorecali.initializer;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kjd.linkedin.explorecali.common.Difficulty;
import kjd.linkedin.explorecali.common.Region;
import kjd.linkedin.explorecali.tour.TourPackageService;
import kjd.linkedin.explorecali.tour.TourService;
import lombok.Data;

@Service
public class DataInitializer {
    Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    
    @Autowired TourPackageService tourPackageService;
	@Autowired TourService tourService;

    public void initialize() throws IOException {
        logger.info("Loading Tour and TourPackge data");
        loadData();
    }

    private void loadData() throws IOException {
        TourData.read("tour/ExploreCalifornia.json")            
            .forEach(this::createData);

        logger.info("Loaded {} tour packages", tourPackageService.total());
        logger.info("Loaded {} tours", tourService.total());
    }

    private void createData(TourData data) {
        String packageCode = Arrays.stream(data.packageType.split("\\s+"))
            .map(w -> w.substring(0, 1))
            .reduce("", (t, u) -> t.concat(u.toUpperCase()));

        tourPackageService.createTourPackage(packageCode, data.packageType);
        tourService.createTour(data.packageType, 
            data.title, 
            data.description, 
            data.blurb, 
            new BigDecimal(data.price), 
            data.length, 
            data.keywords, 
            Region.findByLabel(data.region), 
            Difficulty.findByLabel(data.difficulty));
    }

    @Data
    private static class TourData {
        private String packageType, title, description, blurb, bullets,
            price, length, keywords, difficulty, region;

        static List<TourData> read(String file) throws IOException {
            InputStream is = DataInitializer.class.getClassLoader().getResourceAsStream(file);
            return new ObjectMapper()
                .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
                .readValue(is, new TypeReference<List<TourData>>(){});
        }
    }
}
