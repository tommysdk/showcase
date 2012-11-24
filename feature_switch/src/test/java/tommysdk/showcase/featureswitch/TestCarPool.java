/*
* Copyright 2012 Tommy Tynjä
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package tommysdk.showcase.featureswitch;

import static org.junit.Assert.*;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import tommysdk.showcase.featureswitch.predicate.Always;
import tommysdk.showcase.featureswitch.predicate.Feature;
import tommysdk.showcase.featureswitch.predicate.FileBasedFeatureManager;

import javax.inject.Inject;
import java.util.Date;

/**
 * @author Tommy Tynj&auml;
 */
@RunWith(Arquillian.class)
public class TestCarPool {

    @Deployment
    public static Archive deployment() {
        return ShrinkWrap.create(JavaArchive.class, "carpool.jar")
                .addClass(CarPool.class)
                .addClasses(Car.class, MercedesCLS250.class)
                .addClasses(Feature.class, Always.class)
                .addClass(FileBasedFeatureManager.class)
                .addPackage(Disabled.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private CarPool carPool;

    @Test
    public void shouldHaveAvailableCarsOnStartup() {
        assertTrue(carPool.availableCars() > 0);
    }

    @Test
    public void shouldBeAbleToRentAndReturnCar() {
        int availableCars = carPool.availableCars();
        Car car = carPool.rent();
        assertNotNull(car);
        assertEquals(availableCars - 1, carPool.availableCars());
        carPool.returnCar(car);
    }

    @Test
    public void rentSpecificCarShouldBeDisabled() {
        carPool.rentSpecific(MercedesCLS250.class);
    }

    @Test
    public void bookCarInAdvanceShouldBeDisabled() {
        carPool.book(MercedesCLS250.class, new Date(), 2);
    }

    @Test
    public void shouldInvokeFeatureManager() {
        carPool.rentalCostPerDayFor(MercedesCLS250.class);
    }
}
