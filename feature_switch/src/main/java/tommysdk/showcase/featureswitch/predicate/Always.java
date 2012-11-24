package tommysdk.showcase.featureswitch.predicate;

import tommysdk.showcase.featureswitch.FeatureManager;
import tommysdk.showcase.featureswitch.Predicate;

/**
 * @author Tommy Tynj&auml;
 */
public class Always implements Predicate {

    @Override
    public boolean isDisabled(final String[] properties) {
        return true;
    }

    @Override
    public Always with(final FeatureManager feature) {
        return this;
    }
}
