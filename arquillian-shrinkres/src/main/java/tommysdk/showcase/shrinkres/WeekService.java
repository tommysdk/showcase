package tommysdk.showcase.shrinkres;

import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;

import javax.inject.Named;

/**
 * @author Tommy Tynj&auml;
 */
@Named
public class WeekService {

    public int getWeek() {
        return new DateTime().get(DateTimeFieldType.weekOfWeekyear());
    }

}
