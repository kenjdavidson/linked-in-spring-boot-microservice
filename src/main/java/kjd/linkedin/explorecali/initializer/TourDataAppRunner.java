package kjd.linkedin.explorecali.initializer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import kjd.linkedin.explorecali.tour.TourPackageService;
import kjd.linkedin.explorecali.tour.TourService;
import lombok.Data;

@Component
public class TourDataAppRunner implements ApplicationRunner {
    Logger logger = LoggerFactory.getLogger(TourDataAppRunner.class);
    
    @Value("${ec.importfile}") String importFile;
    @Autowired TourPackageService tourPackageService;
	@Autowired TourService tourService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("Loading Tour and TourPackge data");
        loadData();
    }

    private void loadData() throws IOException {
        TourData.read(importFile)            
            .forEach(this::createData);

        logger.info("Loaded {} tour packages", tourPackageService.total());
        logger.info("Loaded {} tours", tourService.total());
    }

    private void createData(TourData data) {
        String packageCode = Arrays.stream(data.packageName.split("\\s+"))
            .map(w -> w.substring(0, 1))
            .reduce("", (t, u) -> t.concat(u.toUpperCase()));

        tourPackageService.createTourPackage(packageCode, data.packageName);
        tourService.createTour(data.packageName, 
            data.title, 
            data.details);
    }

    @Data
    private static class TourData {
        private String packageName;
        private String title;
        private Map<String,String> details;

        TourData(Map<String,String> record) {
            this.title = record.remove("title");
            this.packageName = record.remove("packageType");
            this.details = record;
        }

        static List<TourData> read(String file) throws IOException {
            InputStream is = TourDataAppRunner.class.getClassLoader().getResourceAsStream(file);
            List<Map<String,String>> records = new ObjectMapper()
                .setVisibility(PropertyAccessor.FIELD, Visibility.ANY)
                .readValue(is, new TypeReference<List<Map<String,String>>>(){});

            return records.stream().map(TourData::new).collect(Collectors.toList());
        }
    }
}
