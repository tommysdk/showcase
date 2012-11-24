package tommysdk.showcase.featureswitch;

/**
 * @author Tommy Tynj&auml;
 */
public interface Predicate {

    boolean isDisabled(final String[] properties);

    Predicate with(final FeatureManager feature);

}
