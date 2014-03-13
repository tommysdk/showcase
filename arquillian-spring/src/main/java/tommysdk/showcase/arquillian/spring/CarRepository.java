package tommysdk.showcase.arquillian.spring;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends CrudRepository<Car, Long> {

    Car findById(final long id);

    List<Car> findByMake(final String make);

}
