package tommysdk.showcase.arquillian.spring;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.spring.integration.test.annotation.SpringConfiguration;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
@SpringConfiguration("applicationContext.xml")
public class CarIntegrationTest {

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(Car.class.getPackage())
                .addAsResource("configuration.properties")
                .setWebXML("web.xml");
    }

    @Autowired
    private CarRepository carRepository;

    @Test
    public void shouldPersistCarInRepository() {
        Car car = carRepository.save(new Car("Mercedes-Benz", "SL500"));
        assertNotNull(car.getId());
    }
}
