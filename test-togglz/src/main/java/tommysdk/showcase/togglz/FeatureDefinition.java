package tommysdk.showcase.togglz;

import org.togglz.core.Feature;
import org.togglz.core.annotation.EnabledByDefault;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

/**
 * @author Tommy Tynj&auml;
 */
public enum FeatureDefinition implements Feature {

    @Label("Application description")
    APPLICATION_DESCRIPTION,

    @EnabledByDefault
    @Label("Author name is visible")
    AUTHOR_NAME_IS_VISIBLE;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }


}
