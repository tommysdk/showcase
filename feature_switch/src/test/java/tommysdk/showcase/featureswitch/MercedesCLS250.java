package tommysdk.showcase.featureswitch;

/**
 * @author Tommy Tynj&auml;
 */
public class MercedesCLS250 implements Car {

    public final String licensePlate;

    public MercedesCLS250(final String licensePlate) {
        this.licensePlate = licensePlate;
    }

    @Override
    public String make() {
        return "Mercedes-Benz";
    }

    @Override
    public String model() {
        return "CLS 250";
    }

    @Override
    public String licensePlate() {
        return licensePlate;
    }
}
