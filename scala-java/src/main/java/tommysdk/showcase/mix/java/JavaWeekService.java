package tommysdk.showcase.mix.java;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.Calendar;

/**
 * @author Tommy Tynj&auml;
 */
@Stateless
@LocalBean
public class JavaWeekService {

    public int currentWeek() {
        return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
    }
}
