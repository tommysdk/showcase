package tommysdk.showcase.featureswitch;

import static org.junit.Assert.*;
import org.junit.Test;
import tommysdk.showcase.featureswitch.predicate.Always;
import tommysdk.showcase.featureswitch.predicate.Feature;

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

    @Test
    public void disabledAnnotationDefaultMethodShouldBeAlwaysDisabled() throws NoSuchMethodException {
        Disabled d = this.getClass().getMethod("defaultDisabledMethod").getAnnotation(Disabled.class);
        assertEquals(Always.class, d.value());
    }

    @Disabled
    public void defaultDisabledMethod() {}

    @Disabled(Always.class)
    public void alwaysDisabledMethod() {}

    @Disabled(Feature.class)
    public void featureSwitchControlledMethod() {}

    @Disabled(value = Feature.class, feature = "arbitraryValue")
    public void featureSwitchControlledMethodWithFeatureProperties() {}

    private boolean isFeatureSwitchDisabledForMethod(final String method) throws NoSuchMethodException {
        return new FeatureSwitch().isDisabled(this.getClass().getMethod(method));
    }

}
