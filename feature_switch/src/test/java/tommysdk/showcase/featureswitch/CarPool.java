package tommysdk.showcase.featureswitch;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.interceptor.Interceptors;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Tommy Tynj&auml;
 */
@Singleton
@Interceptors(FeatureSwitch.class)
public class CarPool {

    private List<Car> pool = new LinkedList<>();

    @PostConstruct
    public void initializePool() {
        pool.add(new MercedesCLS250("CLS250"));
    }

    public Car rent() throws NoSuchElementException {
        if (pool.isEmpty())
            throw new NoSuchElementException("No available cars");
        return pool.remove(0);
    }

    public void returnCar(final Car rentedCar) {
        pool.add(rentedCar);
    }

    public int availableCars() {
        return pool.size();
    }

    // New feature which is not yet implemented
    @Disabled
    public Car rentSpecific(final Class<? extends Car> makeAndModel) {
        throw new IllegalStateException("Method not implemented yet");
    }

    // New feature which is not yet implemented and will probably require some refactoring
    @Disabled
    public void book(final Class<? extends Car> desiredCar, final Date startDate, final int days) {
        throw new IllegalStateException("Method not implemented yet");
    }

}
