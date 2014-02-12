package tommysdk.showcase.test;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.internal.matchers.StringContains.*;

/**
 * @author Tommy Tynj&auml;
 */
public class UserTest {

    private static final String nick = "tt";
    private static final String name = "Tommy";

    @Test
    public void toStringShouldIncludeNickname() {
        User u = new User(nick, name);
        assertThat(u.toString(), containsString(nick));
    }

    @Test
    public void toStringShouldIncludeRealName() {
        User u = new User(nick, name);
        assertThat(u.toString(), containsString(name));
    }

}
