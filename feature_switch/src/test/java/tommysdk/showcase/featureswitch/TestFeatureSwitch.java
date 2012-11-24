package tommysdk.showcase.featureswitch;

import static org.junit.Assert.*;
import org.junit.Test;
import tommysdk.showcase.featureswitch.predicate.Always;

/**
 * @author Tommy Tynj&auml;
 */
public class TestFeatureSwitch {

    @Test
    public void isDisabledShouldNotThrowRuntimeExceptionForAlwaysPredicate() throws NoSuchMethodException {
        assertTrue(isFeatureSwitchDisabledForMethod("alwaysDisabledMethod"));
    }

    @Test(expected = IllegalStateException.class)
    public void isDisabledShouldThrowRuntimeExceptionForFeaturePredicateWhenNoFeatureManagerPresent() throws NoSuchMethodException {
        isFeatureSwitchDisabledForMethod("featureSwitchControlledMethod");
    }

    @Test(expected = IllegalStateException.class)
    public void isDisabledShouldThrowRuntimeExceptionForFeaturePredicateWithPropertiesWhenNoFeatureManagerPresent() throws NoSuchMethodException {
        isFeatureSwitchDisabledForMethod("featureSwitchControlledMethodWithFeatureProperties");
    }

    @Disabled(Always.class)
    public void alwaysDisabledMethod() {}

    @Disabled
    public void featureSwitchControlledMethod() {}

    @Disabled(feature = "arbitraryValue")
    public void featureSwitchControlledMethodWithFeatureProperties() {}

    private boolean isFeatureSwitchDisabledForMethod(final String method) throws NoSuchMethodException {
        return new FeatureSwitch().isDisabled(this.getClass().getMethod(method));
    }

}
