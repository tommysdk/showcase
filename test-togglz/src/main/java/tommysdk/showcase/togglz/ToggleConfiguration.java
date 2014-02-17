package tommysdk.showcase.togglz;

import org.togglz.core.Feature;
import org.togglz.core.manager.TogglzConfig;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.mem.InMemoryStateRepository;
import org.togglz.core.user.UserProvider;
import org.togglz.servlet.user.ServletUserProvider;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Tommy Tynj&auml;
 */
@ApplicationScoped
public class ToggleConfiguration implements TogglzConfig {

    public Class<? extends Feature> getFeatureClass() {
        return FeatureDefinition.class;
    }

    public StateRepository getStateRepository() {
        return new InMemoryStateRepository();
    }

    public UserProvider getUserProvider() {
        return new ServletUserProvider("togglz");
    }

}
