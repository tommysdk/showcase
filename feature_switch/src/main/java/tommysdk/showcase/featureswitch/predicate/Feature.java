package tommysdk.showcase.featureswitch.predicate;

import tommysdk.showcase.featureswitch.FeatureManager;
import tommysdk.showcase.featureswitch.Predicate;

/**
 * @author Tommy Tynj&auml;
 */
public class Feature implements Predicate {

    private FeatureManager feature;

    @Override
    public boolean isDisabled(final String[] properties) {
        return feature.isDisabledAccordingTo(properties);
    }

    public Feature with(final FeatureManager feature) {
        this.feature = feature;
        return this;
    }

}
