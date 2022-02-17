package kjd.linkedin.explorecali.tour;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

public interface TourRepository extends CrudRepository<Tour, String> {
    
    /**
     * Find Tours associated with the Tour Package.
     *
     * @param code tour package code
     * @return List of found tours.
     */
    List<Tour> findByTourPackageCode(@Param("code")String code);

    /**
     * Only return the main fields of a Tour, not the details
     *
     * @param code tour package code
     * @return tours without details
     */
    @Query(value = "{'tourPackageCode' : ?0 }",
            fields = "{ 'id':1, 'title':1, 'tourPackageCode':1, 'tourPackageName':1}")
    List<Tour> findSummaryByTourPackageCode(@Param("code")String code);

    @Override
    @RestResource(exported = false)
    <S extends Tour> S save(S s);

    @Override
    @RestResource(exported = false)
    <S extends Tour> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    @RestResource(exported = false)
    void deleteById(String string);

    @Override
    @RestResource(exported = false)
    void delete(Tour tour);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Tour> iterable);

    @Override
    @RestResource(exported = false)
    void deleteAll();
    
}
