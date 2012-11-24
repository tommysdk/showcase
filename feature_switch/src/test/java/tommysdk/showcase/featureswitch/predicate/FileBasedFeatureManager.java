package tommysdk.showcase.featureswitch.predicate;

import tommysdk.showcase.featureswitch.FeatureManager;

/**
 * @author Tommy Tynj&auml;
 */
public class FileBasedFeatureManager implements FeatureManager {

    @Override
    public boolean isDisabledAccordingTo(final String[] properties) {
        // TODO: Lookup and resolve return value from a property file
        return true;
    }
}
